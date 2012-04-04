package com.fly.sys.db;

import com.fly.common.Callback;
import com.fly.sys.IPDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class JdbcTemplate {
    private Connection conn;

    public JdbcTemplate(Connection conn) {
        this.conn = conn;
    }

    public List<Map<String, Object>> queryForList(String sql) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        System.out.println("------------------------------------------------");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        while (rs.next()) {
            map = new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++) {
                Object obj = rs.getObject(i);
                map.put(md.getColumnLabel(i), obj);
                System.out.println(obj + "  >> " + md.getColumnLabel(i) + " = " + md.getColumnName(i) + " = " + (obj == null ? "" : obj.getClass().toString()));
            }
            System.out.println("-------------------------------------------");
            list.add(map);
        }
        rs.close();

        return list;
    }

    public Map<String, Object> queryForMap(String sql, Object... values) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (null != values && values.length > 0) {
            int i = 1;
            for (Object obj : values) {
                pstmt.setObject(i++, obj);
            }
        }
        ResultSet rs = pstmt.executeQuery();
        Map<String, Object> map = new HashMap<String, Object>();
        while (rs.next()) {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
            }
        }
        rs.close();
        pstmt.close();

        return map;
    }

    public void query(String sql, Callback<ResultSet> callback, Object... values) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (null != values && values.length > 0) {
            int i = 1;
            for (Object obj : values) {
                pstmt.setObject(i++, obj);
            }
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            callback.call(rs);
        }
        rs.close();
        pstmt.close();
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... values) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (null != values && values.length > 0) {
            int i = 1;
            for (Object obj : values) {
                pstmt.setObject(i++, obj);
            }
        }
        ResultSet rs = pstmt.executeQuery();
        List<T> list = new ArrayList<T>();
        T t;
        while (rs.next()) {
            t = rowMapper.mapRow(rs);
            list.add(t);
        }
        rs.close();
        pstmt.close();

        return list;
    }

    public void save(IPDB po) throws Exception {
        Map<String, Map<String, Object>> map = po.getPDBMap();
        for (String table : map.keySet()) {
            save(map.get(table), table);
        }
    }

    public void save(Map<String, Object> params, String table)  throws Exception {
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
            if(null != conn) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw e;
        }
    }

    public void delete(Map<String, Object> params, String table)  throws Exception {
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM " + table + " WHERE ");

            List<String> keyList = new ArrayList<String>();

            int i = 0;
            for(String key : params.keySet()) {
                sql.append(key).append("=?");
                if(++i < params.size()) {
                    sql.append(",");
                }
                keyList.add(key);
            }
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            i = 1;
            for (String key : keyList) {
                pstmt.setObject(i++, params.get(key));
            }
            pstmt.executeUpdate();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            if(null != conn) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw e;
        }
    }

    public void close() {
        if(null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearTable(String sys_dbms_define) throws SQLException {
        String sql = "DELETE FROM " + sys_dbms_define;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    public void update(Map<String, Object> valueMap, Map<String, Object> conditionMap, String tableName) throws Exception {
        try {
            StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

            List<String> keyList = new ArrayList<String>();
            List<String> conditionKeyList = new ArrayList<String>();

            int i = 0;
            for(String key : valueMap.keySet()) {
                sql.append(key).append("=?");
                if(++i < valueMap.size()) {
                    sql.append(",");
                }
                keyList.add(key);
            }
            sql.append(" WHERE 1=1 ");
            for (String key : conditionMap.keySet()) {
                sql.append("AND ").append(key).append("=?");
                conditionKeyList.add(key);
            }
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            i = 1;
            for (String key : keyList) {
                pstmt.setObject(i++, valueMap.get(key));
            }
            for (String key : conditionKeyList) {
                pstmt.setObject(i++, conditionMap.get(key));
            }
            pstmt.executeUpdate();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            if(null != conn) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw e;
        }
    }
}
