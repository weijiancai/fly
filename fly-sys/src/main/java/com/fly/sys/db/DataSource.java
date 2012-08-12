package com.fly.sys.db;

import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsDefine;
import com.fly.sys.db.meta.DbmsSchema;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.util.Callback;
import com.fly.sys.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class DataSource {
    private String name;
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private int sortNum;
    private List<DbmsTable> tableList = new ArrayList<DbmsTable>();
    private Map<String, DbmsColumn> columnNameMap = new HashMap<String, DbmsColumn>();

    private DbmsDefine dbmsDefine;
    private DbmsSchema dbmsSchema;
    private int _sortNum = 10;

    public DataSource() {

    }

    public void loadTables() {
        try {
            if (StringUtil.isNotEmpty(driverClass)) {
                DbmsType dbmsType = getDbmsType();
                if (dbmsType == DbmsType.MYSQL) {
                    loadMysqlTables();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMysqlTables() throws Exception {
        Connection conn = getConn();
        JdbcTemplate template = new JdbcTemplate(conn);
        // 查询所有表
        String sql = "SELECT table_name name, table_comment comment FROM information_schema.TABLES WHERE table_schema=?";
        template.query(sql, new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) {
                try {
                    DbmsTable table = new DbmsTable();
                    table.setSchema(getDbmsSchema());
                    table.setName(rs.getString("name"));
                    table.setAlias(rs.getString("name"));
                    table.setComment(rs.getString("comment"));
                    table.setDisplayName(rs.getString("comment"));
                    table.setValid(true);
                    table.setSortNum(_sortNum);
                    _sortNum += 10;

                    // 保存
                    tableList.add(table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, conn.getCatalog());

        // 插入表sys_dbms_column
        sql = "SELECT * FROM information_schema.COLUMNS WHERE table_schema=? AND table_name=?";
        for (final DbmsTable table : tableList) {
            _sortNum = 10;
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
                        column.setValid(true);
                        column.setSortNum(_sortNum);
                        _sortNum += 10;

                        columnList.add(column);
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
        List<Map<String, Object>> list = template.queryForList(sql, null);
        String tableName, columnName, referencedTableName, referencedColumnName;
        DbmsColumn column, refColumn;
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Map<String, Object> conditionMap = new HashMap<String, Object>();

        Connection sysConn = DBManager.getSysConn();
        JdbcTemplate sysJdbcTemplate = new JdbcTemplate(sysConn);
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
                    sysJdbcTemplate.update(valueMap, conditionMap, "sys_dbms_column");
                    column.setFk(true);
                    column.setFkColumn(refColumn);
                    column.setFkColumnId(refColumn.getId());
                }
            }
        }
        sysConn.commit();
        sysJdbcTemplate.close();

        template.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public Connection getConn() throws Exception {
        Class.forName(getDriverClass());
        return  DriverManager.getConnection(getUrl(), getUsername(), getPassword());
    }

    public List<DbmsTable> getTableList() {
        return tableList;
    }

    public Map<String, DbmsColumn> getColumnNameMap() {
        return columnNameMap;
    }

    public DbmsDefine getDbmsDefine() throws Exception {
        if (dbmsDefine == null) {
            dbmsDefine = new DbmsDefine();
            dbmsDefine.setName(name + "_dbms");
            dbmsDefine.setType(getDbmsType().toString());
            dbmsDefine.setDriverClass(driverClass);
            dbmsDefine.setValid(true);
            dbmsDefine.setSortNum(sortNum);
        }

        return dbmsDefine;
    }

    public DbmsSchema getDbmsSchema() throws Exception {
        if (null == dbmsSchema) {
            dbmsSchema = new DbmsSchema();
            dbmsSchema.setName(name);
            dbmsSchema.setAlias(name);
            dbmsSchema.setVersion("0.0.0");
            dbmsSchema.setValid(true);
            dbmsSchema.setUrl(url);
            dbmsSchema.setDefault(true);
            dbmsSchema.setUserName(username);
            dbmsSchema.setPassword(password);
            dbmsSchema.setSortNum(sortNum);
            dbmsSchema.setDbms(getDbmsDefine());
        }

        return dbmsSchema;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DataSource");
        sb.append("{name='").append(name).append('\'');
        sb.append(", driverClass='").append(driverClass).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", tableList=").append(tableList);
        sb.append('}');
        return sb.toString();
    }

    public DbmsType getDbmsType() {
        if ("com.mysql.jdbc.Driver".equals(driverClass)) {
            return DbmsType.MYSQL;
        }

        return DbmsType.UNKNOWN;
    }
}
