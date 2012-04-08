package com.fly.sys.project;

import com.fly.common.util.DomUtil;
import com.fly.common.util.FileUtil;
import com.fly.sys.config.SysInfo;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.module.ModuleDefine;
import com.fly.sys.module.ModuleManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ProjectManager {
    public static Map<String, ProjectDefine> projectMap = new HashMap<String, ProjectDefine>();

    static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() throws Exception {
        JdbcTemplate template = new JdbcTemplate();

        if (SysInfo.isProjectDefInit()) { // ProjectDef 已经初始化
            String sql = "SELECT * FROM sys_project_define";
            List<ProjectDefine> projectList = template.query(sql, ProjectRowMapperFactory.getProjectDefine());
            for (ProjectDefine project : projectList) {
                projectMap.put(project.getName(), project);
            }
        } else {
            // 请空表sys_project_define
            template.clearTable("sys_project_define");

            List<Map<String, String>> projectList = DomUtil.toList(FileUtil.getFileFromClassPath("/system.xml"), "/system/project");
            List<Map<String, String>> moduleList = DomUtil.toList(FileUtil.getFileFromClassPath("/system.xml"), "/system/project/modules/module");
            // 插入项目
            for (Map<String, String> map : projectList) {
                ProjectDefine projectDefine = new ProjectDefine();
                projectDefine.setName(map.get("name"));
                projectDefine.setProjectDesc(map.get("projectDesc"));
                projectDefine.setPackageName(map.get("packageName"));
                try {
                    projectDefine.setSortNum(Integer.parseInt(map.get("sortNum")));
                } catch (Exception e) {
                    // do nothing
                }
                projectDefine.setValid(true);
                // 插入表sys_project_define
                template.save(ProjectPDBFactory.getProjectDefine(projectDefine));
                projectMap.put(projectDefine.getName(), projectDefine);

                // 插入项目模块
                String moduleName;
                ModuleDefine moduleDefine;
                for (Map<String, String> aMap : moduleList) {
                    moduleName = aMap.get("name");
                    moduleDefine = ModuleManager.getModuleByName(moduleName);
                    if (moduleDefine != null) {
                        ProjectModule projectModule = new ProjectModule();
                        projectModule.setDisplayName(moduleDefine.getDisplayName());
                        projectModule.setSortNum(moduleDefine.getSortNum());
                        projectModule.setModule(moduleDefine);
                        projectModule.setProject(projectDefine);
                        projectModule.setSuperModule(moduleDefine.getSuperModule());
                        projectModule.setValid(true);
                        // 保存
                        template.save(ProjectPDBFactory.getProjectModule(projectModule));
                    }

                }
            }
        }

        template.close();
    }

    public static ProjectDefine getProject(String projectName) {
        return projectMap.get(projectName);
    }
}
