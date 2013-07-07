package com.fly.sys.db.object;

import com.fly.sys.db.object.impl.DBConnectionImpl;
import com.fly.sys.db.object.loader.MySqlLoader;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class DBConnectionTest {
    @Test
    public void testGetResultSet() throws Exception {
        DBConnectionImpl conn = new DBConnectionImpl("root", "7758521", "jdbc:mysql://127.0.0.1:3306/webshop?characterEncoding=utf8", "com.mysql.jdbc.Driver");
        List<Map<String, Object>> list = conn.getResultSet(MySqlLoader.SCHEMAS);
        for (Map<String, Object> map : list) {
            System.out.println("---------------------------------------------------------");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "  -->  " + entry.getValue());
            }
        }
    }
}
