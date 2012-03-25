package com.fly.sys.db;

import org.junit.Test;

/**
 * @author weijiancai
 */
public class DBManagerTest {
    @Test
    public void testInitDBMS() throws Exception {
        DBManager.initDBMS();
    }

    /**
     * 测试获取默认的数据库连接
     */
    @Test
    public void testGetConn() {
        DBManager.getConn();
    }
}
