package com.fly.sys.module;

import com.fly.common.util.DomUtil;
import com.fly.common.util.StringUtil;
import com.fly.common.util.UFile;
import com.fly.sys.clazz.ClassManager;
import com.fly.sys.config.SysInfo;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.project.ProjectPDBFactory;
import com.fly.sys.project.ProjectRowMapperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ModuleManager {
    public static Map<String, ModuleDefine> moduleIdMap = new HashMap<String, ModuleDefine>();
    public static Map<String, ModuleDefine> moduleNameMap = new HashMap<String, ModuleDefine>();

    /*static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void init() throws Exception {
        JdbcTemplate template = new JdbcTemplate();

        if (SysInfo.isModuleDefInit()) {
            String sql = "SELECT * FROM sys_module_define";
            List<ModuleDefine> moduleList = template.query(sql, ProjectRowMapperFactory.getModuleDefine());
            for (ModuleDefine module : moduleList) {
                moduleIdMap.put(module.getId(), module);
                moduleNameMap.put(module.getName(), module);
            }
        } else {
            List<Map<String, String>> list = DomUtil.toList(UFile.getFileFromClassPath("/system.xml"), "/system/modules/module");
            // 插入模块
            template.clearTable("sys_module_define");

            ModuleDefine module;
            for (Map<String, String> map : list) {
                module = new ModuleDefine();
                module.setName(map.get("name"));
                module.setDisplayName(map.get("displayName"));
                module.setSortNum(Integer.parseInt(map.get("sortNum")));
                module.setValid(true);
                module.setClassDefine(ClassManager.getClassDefine(map.get("class")));
                // 保存
                template.save(ProjectPDBFactory.getModule(module));
                moduleIdMap.put(module.getId(), module);
                moduleNameMap.put(module.getName(), module);
            }

            // 更新父模块
            String superModule;
            Map<String, Object> aMap = new HashMap<String, Object>();
            Map<String, Object> conditionMap = new HashMap<String, Object>();
            for (Map<String, String> map : list) {
                superModule = map.get("superModule");
                if (StringUtil.isNotEmpty(superModule)) {
                    aMap.clear();
                    aMap.put("super_module_id", getModuleByName(superModule).getId());
                    conditionMap.clear();
                    conditionMap.put("id", getModuleByName(map.get("name")).getId());
                    // 更新
                    template.update(aMap, conditionMap, "sys_module_define");
                }
            }

//            SysInfo.setModuleDefInit(true);
//            SysInfo.store();
        }

        template.close();
    }

    public static ModuleDefine getModuleById(String moduleId) {
        return moduleIdMap.get(moduleId);
    }

    public static ModuleDefine getModuleByName(String moduleName) {
        return moduleNameMap.get(moduleName);
    }

    public static List<ModuleDefine> getChildrenModule(String superModuleId) {
            List<ModuleDefine> list = new ArrayList<ModuleDefine>();

            for (ModuleDefine module : moduleIdMap.values()) {
                if (superModuleId.equals(module.getSuperModuleId())) {
                    list.add(module);
                }
            }

            return list;
        }
}
