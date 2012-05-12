package com.fly.sys.db;

import com.fly.common.Callback;
import com.fly.common.XML;
import com.fly.common.util.UFile;
import com.fly.sys.config.SysInfo;
import com.fly.sys.db.meta.*;
import com.fly.sys.dict.DictCategory;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    private static Map<String, DbmsDef> dbmsMap = new HashMap<String, DbmsDef>();
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

    public static List<DbmsDefine> dbmsList;

    static {
        // 装载sys数据源
        loadSysDbms();
        // 初始化系统表
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() throws Exception {
        Connection conn = getConn();

        try {
            conn.setAutoCommit(false);
            if (!SysInfo.isDbmsInit()) { // 初始化系统
                if ("mysql".equalsIgnoreCase(conn.getMetaData().getDatabaseProductName())) {
                    initMysqlDBMS(conn);
                }

            } else {
                JdbcTemplate template = new JdbcTemplate(conn);
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

            conn.commit();
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

    private static void initMysqlDBMS(Connection conn) throws Exception {
        final JdbcTemplate template = new JdbcTemplate(conn);

        DatabaseMetaData metaData = conn.getMetaData();
        // 清空表sys_dbms_define
        template.clearTable("sys_r_class_table");
        template.clearTable("sys_dbms_define");

        // 插入表sys_dbms_define
        dbmsDefine = new DbmsDefine();
        dbmsDefine.setName("sys_dbms");
        dbmsDefine.setType(metaData.getDatabaseProductName());
        dbmsDefine.setDriverClass(driverClass);
        dbmsDefine.setValid(true);
        dbmsDefine.setSortNum(sortNum);
        // 保存
        template.save(DbmsPDBFactory.getDbmsDefine(dbmsDefine));
        dbmsNameMap.put(dbmsDefine.getNameKey(), dbmsDefine);
        dbmsList = new ArrayList<DbmsDefine>();
        dbmsList.add(dbmsDefine);

        // 插入表sys_dbms_schema
        final DbmsSchema schema = new DbmsSchema();
        schema.setDbms(dbmsDefine);
        schema.setName("sys");
        schema.setAlias("sys");
        schema.setVersion("0.0.0");
        schema.setValid(true);
        schema.setSortNum(sortNum);
        schema.setUrl(url);
        schema.setDefault(true);
        schema.setUserName(userName);
        schema.setPassword(password);
        // 保存
        template.save(DbmsPDBFactory.getDbmsSchema(schema));
        schemaNameMap.put(schema.getNameKey(), schema);

        // 插入表sys_dbms_table
        final List<DbmsTable> tableList = new ArrayList<DbmsTable>();
        // 查询所有表
        String sql = "SELECT table_name name, table_comment comment FROM information_schema.TABLES WHERE table_schema=?";
        template.query(sql, new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) {
                try {
                    DbmsTable table = new DbmsTable();
                    table.setSchema(schema);
                    table.setName(rs.getString("name"));
                    table.setAlias(rs.getString("name"));
                    table.setComment(rs.getString("comment"));
                    table.setDisplayName(rs.getString("comment"));
                    table.setValid(true);
                    table.setSortNum(sortNum);
                    sortNum += 10;

                    // 保存
                    template.save(DbmsPDBFactory.getDbmsTable(table));
                    tableList.add(table);
                    tableNameMap.put(table.getNameKey(), table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, conn.getCatalog());

        // 插入表sys_dbms_column
        sql = "SELECT * FROM information_schema.COLUMNS WHERE table_schema=? AND table_name=?";
        for (final DbmsTable table : tableList) {
            sortNum = 10;
            final List<DbmsColumn> columnList = new ArrayList<DbmsColumn>();

            template.query(sql, new Callback<ResultSet>() {
                @Override
                public void call(ResultSet rs, Object... obj) {
                    try {
                        DbmsColumn column = new DbmsColumn();
                        column.setTable(table);
                        column.setName(rs.getString("column_name"));
                        column.setAlias(rs.getString("column_name"));
                        column.setComment(rs.getString("column_comment"));
                        column.setDisplayName(rs.getString("column_comment"));
                        column.setDataType(rs.getString("data_type"));
                        column.setDefaultValue(rs.getString("column_default"));
                        column.setMaxLength(rs.getInt("character_maximum_length"));
                        column.setNullable("YES".equalsIgnoreCase(rs.getString("is_nullable")));
                        column.setPk("PRI".equals(rs.getString("column_key")));
                        //column.setFk("MUL".equals(rs.getString("column_key")));
                        column.setValid(true);
                        column.setSortNum(sortNum);
                        sortNum += 10;

                        columnList.add(column);
                        template.save(DbmsPDBFactory.getDbmsColumn(column));
                        columnNameMap.put(column.getNameKey(), column);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, conn.getCatalog(), table.getName());

            table.setColumnList(columnList);
        }

        // 查询外键关系
        sql = "SELECT table_name, column_name, referenced_table_name, referenced_column_name FROM information_schema.KEY_COLUMN_USAGE WHERE table_schema='" + conn.getCatalog() +"'";
        List<Map<String, Object>> list = template.queryForList(sql);
        String tableName, columnName, referencedTableName, referencedColumnName;
        DbmsColumn column, refColumn;
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        for (Map<String, Object> map : list) {
            tableName = map.get("table_name").toString();
            columnName = map.get("column_name").toString();
            Object refTableName = map.get("referenced_table_name");
            Object refColumnName = map.get("referenced_column_name");
            if (refTableName != null && refColumnName != null) {
                referencedTableName = refTableName.toString();
                referencedColumnName = refColumnName.toString();
                column = columnNameMap.get("sys_dbms.sys." + tableName + "." + columnName);
                refColumn = columnNameMap.get("sys_dbms.sys." + referencedTableName + "." + referencedColumnName);
                if (column != null && refColumn != null) {
                    valueMap.clear();
                    valueMap.put("fk_column_id", refColumn.getId());
                    valueMap.put("is_fk", "T");
                    conditionMap.clear();
                    conditionMap.put("id", column.getId());
                    template.update(valueMap, conditionMap, "sys_dbms_column");
                }
            }
        }

        sortNum = 10;
        SysInfo.setDbmsInit(true);
        SysInfo.store();
    }

    private static void loadSysDbms() {
        try {
            sysDbmsProp.load(new InputStreamReader(DBManager.class.getResourceAsStream("/sys_dbms.properties")));
            driverClass = sysDbmsProp.getProperty("driverClass");
            userName = sysDbmsProp.getProperty("userName");
            password = sysDbmsProp.getProperty("password");
            url = sysDbmsProp.getProperty("url");
            if (null == driverClass || null == userName || null == password) {
                throw new Exception("请正确配置sys数据源信息");
            }
        } catch (Exception e) {
            //System.exit(0);
            e.printStackTrace();
        }
    }

    public static void loadDataSource() {
        // 从classpath读取数据源配置文件datasource.xml
        try {
            xml = new XML(UFile.getFileFromClassPath("/datasource.xml"));
            dataSourceMap = xml.toMap("//datasource", "name", DataSource.class);
            dbmsMap = xml.toMap("//dbms", "name", DbmsDef.class);
            dictMap = xml.toMap("//categoryList", "name", DictCategory.class);
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
                //initMysqlDBMS(dbms);
            }
        }
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

    public static Connection getConn() throws Exception {
        Class.forName(driverClass);

        return DriverManager.getConnection(url, userName, password);
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

    public static DbmsDefine getDbms() {
        return dbmsDefine;
    }

    public static DbmsDefine getDbms(String dbmsName) {
        return dbmsNameMap.get(dbmsName);
    }
}
