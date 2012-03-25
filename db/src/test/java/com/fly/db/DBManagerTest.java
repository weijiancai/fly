package com.fly.db;

import com.fly.db.util.DataSource;
import org.junit.Test;

/**
 * @author weijiancai
 */
public class DBManagerTest {
    @Test
    public void testGetDataSource() throws Exception {
        DataSource ds = DBManager.getDataSource("sys");
        System.out.println(ds);
    }

    @Test
    public void testDbms() throws Exception {
        DBManager.initDBMS();
    }
}
