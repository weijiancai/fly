package com.fly.sys.clazz;

import com.fly.common.json.Json;
import com.fly.db.DBManager;
import com.fly.db.util.DataSource;
import com.fly.sys.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public static ClassDef getClassDef(String className) {
        String sql = "";
        return null;
    }
}
