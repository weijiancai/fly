package com.fly.sys.clazz;

import com.fly.db.DBManager;
import com.fly.db.util.DataSource;
import com.fly.sys.R;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * 类定义管理器
 *
 * @author weijiancai
 */
public class ClassDefManager {

    public static void saveClassDef(Map<String, String> params) {
        DataSource ds = DBManager.getDataSource(R.ds.SYS);
        Connection conn = null;
        try {
            conn = ds.getConn();

            String sql = "INSERT INTO sys_class_define () values()";
        } catch (Exception e) {
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

        return null;
    }
}
