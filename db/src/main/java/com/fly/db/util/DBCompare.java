package com.fly.db.util;

import com.fly.db.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库比较
 * 
 * @author weijiancai
 */
public class DBCompare {
    private DBManager manager;
    private Connection srcConn;
    private Connection targetConn;

    public DBCompare(String srcDsName, String targetDsName) {
        manager = new DBManager();
        srcConn = manager.getDataSource(srcDsName).getConn();
        targetConn = manager.getDataSource(targetDsName).getConn();
        try {
            System.out.println(srcConn.getCatalog());
            System.out.println(targetConn.getCatalog());
            //srcConn.close();
            targetConn.close();
            compareTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void compareTable() throws SQLException {
        String sql = "SELECT table_name name FROM information_schema.TABLES WHERE table_schema='" + srcConn.getCatalog() + "'";
        Statement stmt = srcConn.createStatement();
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
        srcConn.close();

        for (Map<String, Object> m : list) {
            System.out.println(m);
        }
    }
}
