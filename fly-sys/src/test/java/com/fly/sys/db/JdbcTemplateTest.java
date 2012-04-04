package com.fly.sys.db;

import com.fly.sys.R;
import org.junit.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class JdbcTemplateTest {
    @Test
    public void testDelete() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "DbmsDefine");
        Connection conn = DBManager.getDataSource(R.ds.SYS).getConn();
        conn.setAutoCommit(false);
        JdbcTemplate template = new JdbcTemplate(conn);
        template.delete(map, "sys_class_define");
        conn.commit();
    }
}
