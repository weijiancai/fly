package com.fly.sys.clazz;

import com.fly.sys.R;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.DataSource;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 类定义管理器
 *
 * @author weijiancai
 */
public class ClassDefManager {
    public static void saveClassDef(Map<String, Object> params) {
        Map<String, Object> classMap = new HashMap<String, Object>();
        String classDefId = UUID.randomUUID().toString().replaceAll("-", "");
        classMap.put("id", classDefId);
        classMap.put("name", "ClassDefine"); // 类名
        classMap.put("cname", "类定义"); // 中文名
        classMap.put("author", "weijiancai"); // 作者
        classMap.put("class_desc", "类定义信息"); // 类描述
        classMap.put("version", "1.0");  // 版本

        //save(classMap, "sys_class_define");
    }

    // 更新数据库表信息
    public static void update(Map<String, Object> params, String table) {

    }

    public static ClassDefine getClassDef(final String className) {
        DataSource ds = DBManager.getDataSource(R.ds.SYS);
        Connection conn = null;
        final ClassDefine def = new ClassDefine();
        try {
            conn = ds.getConn();
            conn.setAutoCommit(false);
            String sql = "SELECT * FROM sys_class_define WHERE name=?";

            JdbcTemplate template = new JdbcTemplate(conn);
            template.query(sql, new Callback<ResultSet>() {
                @Override
                public void call(ResultSet rs, Object... obj) {
                    try {
                        def.setId(rs.getString(R.classdef.id));
                        def.setName(className);
                        def.setCname(rs.getString(R.classdef.cname));
                        def.setAuthor(rs.getString(R.classdef.author));
                        def.setVersion(rs.getString(R.classdef.version));
                        def.setDesc(rs.getString(R.classdef.class_desc));
                        def.setSuperClass(rs.getString(R.classdef.super_class));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, className);

            // 查询字段列表
            Map<String, ClassField> fieldMap = new HashMap<String, ClassField>();
            def.setFieldList(getFieldList(template, def, fieldMap));
            def.setFieldMap(fieldMap);
            // 查询form列表
            Map<String, ClassForm> formMap = new HashMap<String, ClassForm>();
            def.setFormList(getFormLIst(template, def, formMap));
            def.setFormMap(formMap);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(null != conn) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            if(null != conn) {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return def;
    }

    private static List<ClassField> getFieldList(JdbcTemplate template, final ClassDefine classDef, final Map<String, ClassField> fieldMap) throws Exception {
        final List<ClassField> list = new ArrayList<ClassField>();

        template.query("SELECT * FROM sys_class_field WHERE class_id=?", new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) {
                try {
                    ClassField field = new ClassField();
                    field.setId(rs.getString("id"));
                    //field.setClassId(classDef.getId());
                    //field.setColId(rs.getString("col_id"));
                    field.setFieldDesc(rs.getString("field_desc"));
                    field.setName(rs.getString("name"));
                    field.setType(rs.getString("type"));
                    field.setClassDef(classDef);

                    list.add(field);
                    fieldMap.put(field.getId(), field);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, classDef.getId());

        return list;
    }

    private static List<ClassForm> getFormLIst(JdbcTemplate template, final ClassDefine classDef, final Map<String, ClassForm> formMap) throws Exception {
        final List<ClassForm> list = new ArrayList<ClassForm>();

        String sql = "SELECT * FROM sys_class_form WHERE class_id=?";
        template.query(sql, new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) {
                try {
                    ClassForm form = new ClassForm();
                    form.setId(rs.getString("id"));
                    form.setName(rs.getString("name"));
                    form.setColCount(rs.getInt("col_Count"));
                    form.setColWidth(rs.getInt("col_width"));
                    form.setLabelGap(rs.getInt("label_gap"));
                    form.setFieldGap(rs.getInt("field_gap"));
                    form.setClassDefine(classDef);

                    list.add(form);
                    formMap.put(form.getId(), form);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, classDef.getId());

        return list;
    }

    private static List<FormField> getFormFieldList(JdbcTemplate template, final ClassForm classForm) throws Exception {
        final List<FormField> list = new ArrayList<FormField>();

        String sql = "SELECT * FROM sys_class_form_field a, sys_form_field_append b WHERE a.id=b.form_field_id and form_id=?";
        template.query(sql, new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) {
                try {
                    FormField field = new FormField();
                    field.setId(rs.getString("id"));
                    field.setClassForm(classForm);
                    field.setDisplayName(rs.getString("display_name"));
                    field.setDisplayStyle(rs.getInt("display_style"));
                    field.setHeight(rs.getInt("height"));
                    field.setWidth(rs.getInt("width"));
                    field.setSingleLine("T".equals(rs.getString("is_single_line")));
                    field.setQueryMode(rs.getInt("query_mode"));

                    list.add(field);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, classForm.getId());

        return list;
    }
}
