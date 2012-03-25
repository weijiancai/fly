package com.fly.sys.clazz;

import com.fly.common.Callback;
import com.fly.sys.R;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.DataSource;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.db.meta.Column;
import com.fly.sys.db.meta.Table;

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
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void saveClassDef(Map<String, Object> params) {
        Map<String, Object> classMap = new HashMap<String, Object>();
        String classDefId = UUID.randomUUID().toString().replaceAll("-", "");
        classMap.put("id", classDefId);
        classMap.put("name", "ClassDef"); // 类名
        classMap.put("cname", "类定义"); // 中文名
        classMap.put("author", "weijiancai"); // 作者
        classMap.put("class_desc", "类定义信息"); // 类描述
        classMap.put("version", "1.0");  // 版本

        //save(classMap, "sys_class_define");
    }
    
    // 数据库列转换为对象字段
    public static void columnToField(String className, String... tableNames) {
        // 更新已有字段信息
        // 插入新字段信息
        DataSource ds = DBManager.getDataSource(R.ds.SYS);
        Table table;
        List<Column> columnList;
        Map<String, Object> params;
        for (String tableName : tableNames) {
            table = ds.getTable(tableName);
            columnList = table.getColumnList();
            params = new HashMap<String, Object>();
            for (Column column : columnList) {
                params.put("id", getUUID());
            }
        }
    }

    // 更新数据库表信息
    public static void update(Map<String, Object> params, String table) {

    }

    public static ClassDef getClassDef(final String className) {
        DataSource ds = DBManager.getDataSource(R.ds.SYS);
        Connection conn = null;
        final ClassDef def = new ClassDef();
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

            sql = "SELECT * FROM sys_class_form WHERE class_id=?";
            final List<ClassForm> formList = new ArrayList<ClassForm>();
            template.query(sql, new Callback<ResultSet>() {
                @Override
                public void call(ResultSet rs, Object... obj) {
                    try {
                        ClassForm form = new ClassForm();
                        form.setId(rs.getString("id"));
                        form.setClassId(rs.getString("class_id"));
                        form.setName(rs.getString("name"));
                        form.setColCount(rs.getInt("col_Count"));
                        form.setColWidth(rs.getInt("col_width"));
                        form.setLabelGap(rs.getInt("label_gap"));
                        form.setFieldGap(rs.getInt("field_gap"));
                        form.setDefault(rs.getInt("isDefault") == 1);
                        formList.add(form);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, def.getId());

            def.setFormList(formList);

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
}
