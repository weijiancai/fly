package com.fly.sys.db;

import com.fly.sys.config.SysInfo;
import com.fly.sys.db.meta.*;
import com.fly.sys.dict.DictCategory;
import com.fly.sys.util.UFile;
import com.fly.sys.util.XML;

import java.sql.Connection;
import java.util.*;

/**
 * 数据库管理器
 *
 * @author weijiancai
 */
public class DBManager {
    private static Properties sysDbmsProp = new Properties();
    private static XML xml;
    private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
    private static Map<String, DictCategory> dictMap = new HashMap<String, DictCategory>();
    private static int sortNum = 10;

    public static String driverClass;
    public static String userName;
    public static String password;
    public static String url;
    private static DbmsDefine dbmsDefine;

    private static Map<String, DbmsDefine> dbmsNameMap = new HashMap<String, DbmsDefine>();
    private static Map<String, DbmsSchema> schemaNameMap = new HashMap<String, DbmsSchema>();
    private static Map<String, DbmsTable> tableNameMap = new HashMap<String, DbmsTable>();
    private static Map<String, DbmsColumn> columnNameMap = new HashMap<String, DbmsColumn>();
    private static Map<String, DbmsColumn> columnIdMap = new HashMap<String, DbmsColumn>();

    public static List<DbmsDefine> dbmsList = new ArrayList<DbmsDefine>();

    static {
        // 装载数据源
        loadDataSource();
        // 初始化系统表
        /*try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void init() throws Exception {
        Connection conn = getSysConn();

        try {
            JdbcTemplate template = new JdbcTemplate(conn);
            if (!SysInfo.isDbmsInit()) { // 初始化系统
                // 清空表sys_dbms_define
                template.clearTable("sys_r_class_table");
                template.clearTable("sys_dbms_define");
                for (DataSource dataSource : dataSourceMap.values()) {
                    initDBMS(dataSource, template);
                }

                conn.commit();
//              SysInfo.setDbmsInit(true);
//              SysInfo.store();
            } else {
                // 查询sys_dbms_define
                String sql = "SELECT * FROM sys_dbms_define";
                dbmsList = template.query(sql, DbmsRowMapperFactory.getDbmsDefine());

                for (DbmsDefine dbms : dbmsList) {
                    dbmsNameMap.put(dbms.getNameKey(), dbms);

                    // 查询sys_dbms_schema
                    sql = "SELECT * FROM sys_dbms_schema WHERE dbms_id=?";
                    List<DbmsSchema> schemaList = template.query(sql, DbmsRowMapperFactory.getDbmsSchema(dbms), dbms.getId());
                    dbms.setSchemaList(schemaList);
                    // 查询sys_dbms_table
                    sql = "SELECT * FROM sys_dbms_table WHERE schema_id=?";
                    for (DbmsSchema schema : schemaList) {
                        schemaNameMap.put(schema.getNameKey(), schema);

                        List<DbmsTable> tableList = template.query(sql, DbmsRowMapperFactory.getDbmsTable(schema), schema.getId());
                        schema.setTableList(tableList);

                        // 查询sys_dbms_column
                        sql = "SELECT * FROM sys_dbms_column WHERE table_id=?";
                        for (DbmsTable table : tableList) {
                            tableNameMap.put(table.getNameKey(), table);

                            List<DbmsColumn> columnList = template.query(sql, DbmsRowMapperFactory.getDbmsColumn(table), table.getId());
                            table.setColumnList(columnList);

                            for (DbmsColumn column : columnList) {
                                columnNameMap.put(column.getNameKey(), column);
                                columnIdMap.put(column.getId(), column);
                            }
                        }
                    }

                    if ("sys_dbms".equals(dbms.getName())) {
                        dbmsDefine = dbms;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != conn) {
                conn.rollback();
            }
        } finally {
            if (null != conn) {
                conn.close();
            }
        }
    }

    private static void initDBMS(DataSource dataSource, JdbcTemplate template) throws Exception {
        dataSource.loadTables();

        // 插入表sys_dbms_define
        DbmsDefine dbmsDef = dataSource.getDbmsDefine();
        // 保存
        template.save(DbmsPDBFactory.getDbmsDefine(dbmsDef));
        dbmsNameMap.put(dbmsDef.getNameKey(), dbmsDef);
        dbmsList.add(dbmsDef);

        // 插入表sys_dbms_schema
        final DbmsSchema schema = dataSource.getDbmsSchema();
        // 保存
        template.save(DbmsPDBFactory.getDbmsSchema(schema));
        schemaNameMap.put(schema.getNameKey(), schema);
        List<DbmsSchema> schemaList = new ArrayList<DbmsSchema>();
        schemaList.add(schema);
        dbmsDef.setSchemaList(schemaList);

        // 插入表sys_dbms_table
        final List<DbmsTable> tableList = dataSource.getTableList();
        for (DbmsTable dbmsTable : tableList) {
            template.save(DbmsPDBFactory.getDbmsTable(dbmsTable));
            tableNameMap.put(dbmsTable.getNameKey(), dbmsTable);

            for (DbmsColumn dbmsColumn : dbmsTable.getColumnList()) {
                template.save(DbmsPDBFactory.getDbmsColumn(dbmsColumn));
            }
        }
        schema.setTableList(tableList);

        columnNameMap.putAll(dataSource.getColumnNameMap());
        if ("sys_dbms".equals(dbmsDef.getName())) {
            dbmsDefine = dbmsDef;
        }
    }

    public static void loadDataSource() {
        // 从classpath读取数据源配置文件datasource.xml
        try {
            xml = new XML(UFile.getFileFromClassPath("/datasource.xml"));
            dataSourceMap = xml.toMap("//datasource", "name", DataSource.class);
            dictMap = xml.toMap("//categoryList", "name", DictCategory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(String dsName) {
        return dataSourceMap.get(dsName);
    }

    public static Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    /**
     * 初始化字典
     */
    private static void initDict() {
        for (DictCategory category : dictMap.values()) {
            // 保存类别

        }
    }

    private static void loadDbms() {
        // 1. 从数据库中装载Dbms信息

        // 2. 从配置文件中装载Dbms信息
        // 3. 如果数据库中没有文件中的信息，则将文件中的信息导入到数据库
    }

    public static Connection getSysConn() throws Exception {
        return getDataSource("sys").getConn();
    }

    /**
     * 根据columnId获取列信息
     *
     * @param columnId sys_dbms_column ID
     * @return 返回column信息
     */
    public static DbmsColumn getColumnById(String columnId) {
        return columnIdMap.get(columnId);
    }

    public static DbmsDefine getSysDbms() throws Exception {
        return dbmsDefine;
    }

    public static DbmsDefine getDbms(String dbmsName) {
        return dbmsNameMap.get(dbmsName);
    }

    public static Map<String, DbmsColumn> getColumnIdMap() {
        return columnIdMap;
    }

    public static List<DbmsDefine> getDbmsList() {
        return dbmsList;
    }
}
