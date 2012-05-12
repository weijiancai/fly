package com.fly.db;

import com.fly.common.XML;
import com.fly.common.util.UFile;
import com.fly.db.meta.DbmsDef;
import com.fly.db.meta.Schema;
import com.fly.db.util.DataSource;
import com.fly.db.util.JdbcTemplate;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库管理器
 *
 * @author weijiancai
 */
public class DBManager {
    private static XML xml;
    private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
    private static Map<String, DbmsDef> dbmsMap = new HashMap<String, DbmsDef>();

    static {
        loadDataSource();
    }

    public static void loadDataSource() {
        // 从classpath读取数据源配置文件datasource.xml
        try {
            xml = new XML(UFile.getFileFromClassPath("/datasource.xml"));
            dataSourceMap = xml.toMap("//datasource", "name", DataSource.class);
            dbmsMap = xml.toMap("//dbms", "name", DbmsDef.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBManager() {

    }

    public static DataSource getDataSource(String dsName) {
        DataSource ds = dataSourceMap.get(dsName);
        if(null == ds) {
            return null;
        }
        if (ds.getTableList() == null || ds.getTableList().size() == 0) {
            ds.loadTables();
        }

        return ds;
    }

    /**
     * 初始化数据库
     * @throws Exception exception
     */
    public static void initDBMS() throws Exception {
        for (DbmsDef dbms : dbmsMap.values()) {
            dbms.setSchemaList(xml.toList("//dbms[@name='" + dbms.getName() + "']/schemaList/schema", Schema.class));
            System.out.println(dbms);
            if ("mysql".equals(dbms.getType())) {
                initMysqlDBMS(dbms);
            }
        }
    }

    private static void initMysqlDBMS(DbmsDef dbms) {
        Connection conn = dbms.getConn();
        JdbcTemplate template = new JdbcTemplate(conn);
        try {
            template.save(dbms, "sys_dbms_define");

        } finally {
            template.close();
        }
    }
}
