package com.fly.sys.clazz;

import com.fly.common.Callback;
import com.fly.common.json.Json;
import com.fly.db.DBManager;
import com.fly.db.util.DataSource;
import com.fly.db.util.JdbcTemplate;
import com.fly.sys.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        DataSource ds = DBManager.getDataSource(R.ds.SYS);
        Connection conn = null;
        try {
            conn = ds.getConn();
            conn.setAutoCommit(false);
            StringBuilder sql = new StringBuilder("INSERT INTO sys_class_define (");

            String[] strs;
            Set<String> set;
            Map<String, Set<String>> clazzMap = new HashMap<String, Set<String>>();
            List<String> keyList = new ArrayList<String>();
            /*for(String key : params.keySet()) {
                strs = key.split(".");
                if (clazzMap.containsKey(strs[0])) {
                    clazzMap.get(strs[0]).add(strs[1]);
                } else {
                    set = new HashSet<String>();
                    set.add(strs[0]);
                    clazzMap.put(strs[0], set);
                }
            }*/
            
            String values = "";
            int i = 0;
            for(String key : params.keySet()) {
                sql.append(key);
                values += "?";
                if(++i < params.size()) {
                    sql.append(",");
                    values += ",";
                }
                keyList.add(key);
            }
            sql.append(") VALUES (").append(values).append(")");
            //StringBuilder sql = "INSERT INTO sys_class_define () values()";
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            i = 1;
            for (String key : keyList) {
                pstmt.setObject(i++, params.get(key));
            }
            pstmt.executeUpdate();
            pstmt.close();
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
    }

    public static void save(Map<String, Object> params, String table) {
        DataSource ds = DBManager.getDataSource(R.ds.SYS);
        Connection conn = null;
        try {
            conn = ds.getConn();
            conn.setAutoCommit(false);
            StringBuilder sql = new StringBuilder("INSERT INTO " + table + " (");

            List<String> keyList = new ArrayList<String>();

            String values = "";
            int i = 0;
            for(String key : params.keySet()) {
                sql.append(key);
                values += "?";
                if(++i < params.size()) {
                    sql.append(",");
                    values += ",";
                }
                keyList.add(key);
            }
            sql.append(") VALUES (").append(values).append(")");
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            i = 1;
            for (String key : keyList) {
                pstmt.setObject(i++, params.get(key));
            }
            pstmt.executeUpdate();
            pstmt.close();
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

            /*PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, className);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                def = new ClassDef();
                def.setId(rs.getString(R.classdef.id));
                def.setName(className);
                def.setCname(rs.getString(R.classdef.cname));
                def.setAuthor(rs.getString(R.classdef.author));
                def.setVersion(rs.getString(R.classdef.version));
                def.setDesc(rs.getString(R.classdef.class_desc));
                def.setSuperClass(rs.getString(R.classdef.super_class));
                def.setColCount(rs.getInt(R.classdef.col_count));
                def.setColWidth(rs.getInt(R.classdef.col_width));
            }
            rs.close();
            pstmt.close();*/
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
