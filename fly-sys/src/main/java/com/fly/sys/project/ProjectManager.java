package com.fly.sys.project;

import com.fly.sys.config.SysInfo;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.dict.CodeManager;
import com.fly.sys.module.ModuleDefine;
import com.fly.sys.module.ModuleManager;
import com.fly.sys.util.DomUtil;
import com.fly.sys.util.UFile;
import com.fly.sys.util.UString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ProjectManager {
    public static Map<String, ProjectDefine> projectMap = new HashMap<String, ProjectDefine>();
    public static Map<String, ProjectDefine> projectUrlMap = new HashMap<String, ProjectDefine>();
    public static Map<String, List<ProjectModule>> projectModuleMap = new HashMap<String, List<ProjectModule>>();

    /*static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void init() throws Exception {
        JdbcTemplate template = new JdbcTemplate();

        if (SysInfo.isProjectDefInit()) { // ProjectDef 已经初始化
            String sql = "SELECT * FROM sys_project_define";
            List<ProjectDefine> projectList = template.query(sql, ProjectRowMapperFactory.getProjectDefine());
            for (ProjectDefine project : projectList) {
                projectMap.put(project.getName(), project);
                projectUrlMap.put(project.getProjectUrl(), project);
                // 查询项目模块
                sql = String.format("SELECT * FROM sys_project_module WHERE project_id='%s'", project.getId());
                List<ProjectModule> list = template.query(sql, ProjectRowMapperFactory.getProjectModule());
                project.setModuleList(list);
                projectModuleMap.put(project.getId(), list);
            }
        } else {
            // 请空表sys_project_define
            template.clearTable("sys_project_define");

            List<Map<String, String>> projectList = DomUtil.toList(UFile.toString("/system.xml"), "/system/project");
            List<Map<String, String>> moduleList = DomUtil.toList(UFile.toString("/system.xml"), "/system/project/modules/module");
            // 插入项目
            for (Map<String, String> map : projectList) {
                ProjectDefine projectDefine = new ProjectDefine();
                projectDefine.setName(map.get("name"));
                projectDefine.setProjectDesc(map.get("projectDesc"));
                projectDefine.setPackageName(map.get("packageName"));
                projectDefine.setProjectUrl(map.get("projectUrl"));
                try {
                    projectDefine.setSortNum(Integer.parseInt(map.get("sortNum")));
                } catch (Exception e) {
                    // do nothing
                }
                projectDefine.setValid(true);
                // 插入表sys_project_define
                template.save(ProjectPDBFactory.getProjectDefine(projectDefine));
                projectMap.put(projectDefine.getName(), projectDefine);
                projectUrlMap.put(projectDefine.getProjectUrl(), projectDefine);

                // 插入项目模块
                String moduleName, superModuleName, level;
                ModuleDefine moduleDefine, superModule;
                List<ProjectModule> list = new ArrayList<ProjectModule>();
                for (Map<String, String> aMap : moduleList) {
                    moduleName = aMap.get("name");
                    superModuleName = aMap.get("superModule");
                    moduleDefine = ModuleManager.getModuleByName(moduleName);
                    superModule = ModuleManager.getModuleByName(superModuleName);
                    if (moduleDefine != null) {
                        ProjectModule projectModule = new ProjectModule();
                        projectModule.setDisplayName(moduleDefine.getDisplayName());
                        projectModule.setSortNum(moduleDefine.getSortNum());
                        projectModule.setModule(moduleDefine);
                        projectModule.setModuleId(moduleDefine.getId());
                        projectModule.setProject(projectDefine);
                        projectModule.setSuperModule(superModule);
                        if (superModule != null) {
                            projectModule.setSuperModuleId(superModule.getId());
                        }
                        projectModule.setValid(true);
                        // 保存
                        template.save(ProjectPDBFactory.getProjectModule(projectModule));
                        list.add(projectModule);
                    }
                }
                projectDefine.setModuleList(list);
                projectModuleMap.put(projectDefine.getId(), list);
            }
        }

        template.commit();
        template.close();
        // 设置数据字典根类别
        for (ProjectDefine project : projectMap.values()) {
            project.setRootCategory(CodeManager.rootCategory);
        }
    }

    public static ProjectDefine getProject(String projectName) {
        return projectMap.get(projectName);
    }

    public static ProjectDefine getProjectByUrl(String url) {
        return projectUrlMap.get(url);
    }

    public static List<ProjectModule> getChildrenModule(String projectId, String superModuleId) {
        List<ProjectModule> list = new ArrayList<ProjectModule>();

        if (!UString.isEmpty(projectId)) {
            for (ProjectModule module : projectModuleMap.get(projectId)) {
                if (superModuleId.equals(module.getSuperModuleId())) {
                    list.add(module);
                }
            }
        }

        return list;
    }
}
