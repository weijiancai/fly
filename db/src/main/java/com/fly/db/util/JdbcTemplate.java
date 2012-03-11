package com.fly.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
}
