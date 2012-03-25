package com.fly.sys.db;

import com.fly.common.XML;
import com.fly.common.util.FileUtil;
import com.fly.sys.db.meta.DbmsDef;
import com.fly.sys.db.meta.Schema;
import com.fly.sys.dict.Category;

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
    private static Map<String, Category> dictMap = new HashMap<String, Category>();
    private static DbmsDef defaultDbms;
    private static Schema defaultSchema;

    static {
        loadDataSource();
        // 装载Dbms
    }

    public static void loadDataSource() {
        // 从classpath读取数据源配置文件datasource.xml
        try {
            xml = new XML(FileUtil.getFileFromClassPath("/datasource.xml"));
            dataSourceMap = xml.toMap("//datasource", "name", DataSource.class);
            dbmsMap = xml.toMap("//dbms", "name", DbmsDef.class);
            dictMap = xml.toMap("//categoryList", "name", Category.class);
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

    /**
     * 初始化字典
     */
    private static void initDict() {
        for (Category category : dictMap.values()) {
            // 保存类别

        }
    }

    private static void initMysqlDBMS(DbmsDef dbms) throws Exception {
        Connection conn = null;
        JdbcTemplate template = new JdbcTemplate(conn);
        try {
            template.save(dbms);

            conn.commit();
        } finally {
            template.close();
        }
    }

    private static void loadDbms() {
        // 1. 从数据库中装载Dbms信息

        // 2. 从配置文件中装载Dbms信息
        // 3. 如果数据库中没有文件中的信息，则将文件中的信息导入到数据库
    }

    public static Connection getConn() {
        // 获得默认的Dbms
        // 获得默认的Schema
        return null;
    }
}
