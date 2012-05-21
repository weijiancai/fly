package com.fly.sys.project;

import com.fly.sys.clazz.ClassManager;
import com.fly.sys.db.RowMapper;
import com.fly.sys.module.ModuleDefine;
import com.fly.sys.module.ModuleManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weijiancai
 */
public class ProjectRowMapperFactory {
    public static RowMapper<ProjectDefine> getProjectDefine() {
        return new RowMapper<ProjectDefine>() {
            @Override
            public ProjectDefine mapRow(ResultSet rs) throws SQLException {
                ProjectDefine projectDefine = new ProjectDefine();
                projectDefine.setId(rs.getString("id"));
                projectDefine.setName(rs.getString("name"));
                projectDefine.setProjectDesc(rs.getString("project_desc"));
                projectDefine.setPackageName(rs.getString("package_name"));
                projectDefine.setSortNum(rs.getInt("sort_num"));
                projectDefine.setValid("T".equals(rs.getString("is_valid")));
                projectDefine.setInputDate(rs.getDate("input_date"));
                projectDefine.setProjectUrl(rs.getString("project_url"));

                return projectDefine;
            }
        };
    }

    public static RowMapper<ModuleDefine> getModuleDefine() {
        return new RowMapper<ModuleDefine>() {
            @Override
            public ModuleDefine mapRow(ResultSet rs) throws SQLException {
                ModuleDefine module = new ModuleDefine();
                module.setId(rs.getString("id"));
                module.setName(rs.getString("name"));
                module.setDisplayName(rs.getString("display_name"));
                module.setInputDate(rs.getDate("input_date"));
                module.setSortNum(rs.getInt("sort_num"));
                module.setSuperModuleId(rs.getString("super_module_id"));
                module.setValid("T".equals(rs.getString("is_valid")));
                module.setClassId(rs.getString("class_id"));

                module.setClassDefine(ClassManager.getClassDefineById(module.getClassId()));

                return module;
            }
        };
    }

    public static RowMapper<ProjectModule> getProjectModule() {
            return new RowMapper<ProjectModule>() {
                @Override
                public ProjectModule mapRow(ResultSet rs) throws SQLException {
                    ProjectModule module = new ProjectModule();
                    module.setProjectId(rs.getString("project_id"));
                    module.setDisplayName(rs.getString("display_name"));
                    module.setInputDate(rs.getDate("input_date"));
                    module.setSortNum(rs.getInt("sort_num"));
                    module.setModuleId(rs.getString("module_id"));
                    module.setSuperModuleId(rs.getString("super_module_id"));
                    module.setValid("T".equals(rs.getString("is_valid")));

                    module.setModule(ModuleManager.getModuleById(rs.getString("module_id")));
                    module.setSuperModule(ModuleManager.getModuleById(rs.getString("super_module_id")));

                    return module;
                }
            };
        }
}
