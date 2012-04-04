package com.fly.sys.db;

import com.fly.sys.db.meta.DbmsDefine;
import org.junit.Test;

import java.sql.Connection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 */
public class DBManagerTest {
    @Test
    public void testInitDBMS() throws Exception {
        //SysInfo.setDbmsInit(false);

        //DBManager.init();
        DbmsDefine dbms = DBManager.getDbms();
    }

    /**
     * 测试获取默认的数据库连接
     */
    @Test
    public void testGetConn() throws Exception {
        Connection conn = DBManager.getConn();
        assertThat(conn, notNullValue());
    }
}
