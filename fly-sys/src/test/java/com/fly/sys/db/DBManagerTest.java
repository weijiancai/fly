package com.fly.sys.db;

import org.junit.Test;

/**
 * @author weijiancai
 */
public class DBManagerTest {
    @Test
    public void testInitDBMS() throws Exception {
        //SysInfo.setDbmsInit(false);

        /*DBManager.init();
        DbmsDefine dbms = DBManager.getSysDbms();*/
    }

    /**
     * 测试获取默认的数据库连接
     */
    @Test
    public void testGetConn() throws Exception {
        /*Connection conn = DBManager.getSysConn();
        assertThat(conn, notNullValue());*/
    }

    @Test
    public void testLoadDataSource() {
        /*DBManager.loadDataSource();
        assertThat(DBManager.getDataSourceMap().size(), equalTo(2));*/
    }
}
