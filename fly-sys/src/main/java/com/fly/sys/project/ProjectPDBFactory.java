package com.fly.sys.project;

import com.fly.sys.IPDB;
import com.fly.sys.module.ModuleDefine;
import com.fly.sys.util.UUIDUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ProjectPDBFactory {
    public static IPDB getProjectDefine(final ProjectDefine projectDefine) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                projectDefine.setId(UUIDUtil.getUUID());
                projectDefine.setInputDate(new Date());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", projectDefine.getId());
                map.put("name", projectDefine.getName());
                map.put("project_desc", projectDefine.getProjectDesc());
                map.put("package_name", projectDefine.getPackageName());
                map.put("is_valid", projectDefine.isValid() ? "T" : "F");
                map.put("input_date", projectDefine.getInputDate());
                map.put("sort_num", projectDefine.getSortNum());
                map.put("project_url", projectDefine.getProjectUrl());

                result.put("sys_project_define", map);

                return result;
            }
        };
    }

    public static IPDB getModule(final ModuleDefine module) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                module.setId(UUIDUtil.getUUID());
                module.setInputDate(new Date());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", module.getId());
                map.put("name", module.getName());
                map.put("display_name", module.getDisplayName());
                if (module.getSuperModule() != null) {
                    map.put("super_module_id", module.getSuperModule().getId());
                }
                map.put("is_valid", module.isValid() ? "T" : "F");
                map.put("input_date", module.getInputDate());
                map.put("sort_num", module.getSortNum());

                result.put("sys_module_define", map);

                return result;
            }
        };
    }

    public static IPDB getProjectModule(final ProjectModule module) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                module.setInputDate(new Date());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("project_id", module.getProject().getId());
                map.put("display_name", module.getDisplayName() == null ? module.getModule().getDisplayName() : module.getDisplayName());
                map.put("module_id", module.getModule().getId());
                if (module.getSuperModuleId() != null) {
                    map.put("super_module_id", module.getSuperModuleId());
                } else {
                    if (module.getSuperModule() != null) {
                        map.put("super_module_id", module.getSuperModule().getId());
                    }
                }
                map.put("is_valid", module.isValid() ? "T" : "F");
                map.put("input_date", module.getInputDate());
                map.put("sort_num", module.getSortNum());

                result.put("sys_project_module", map);

                return result;
            }
        };
    }

    public static IPDB getModuleTpl(final ModuleDefine module) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                module.setId(UUIDUtil.getUUID());
                module.setInputDate(new Date());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", module.getId());
                map.put("name", module.getName());
                map.put("display_name", module.getDisplayName());
                if (module.getSuperModule() != null) {
                    map.put("super_module_id", module.getSuperModule().getId());
                }
                map.put("is_valid", module.isValid() ? "T" : "F");
                map.put("input_date", module.getInputDate());
                map.put("sort_num", module.getSortNum());

                result.put("sys_module_define", map);

                return result;
            }
        };
    }
}
