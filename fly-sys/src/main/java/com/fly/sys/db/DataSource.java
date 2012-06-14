package com.fly.sys.db;

import com.fly.common.Callback;
import com.fly.common.util.StringUtil;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private List<DbmsTable> tableList;
    private Map<String, DbmsTable> tableMap = new HashMap<String, DbmsTable>();

    public DataSource(String name, String driverClass, String url, String username, String password) {
        this.name = name;
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public DataSource() {
    }

    public void loadTables() {
        try {
            if (StringUtil.isNotEmpty(driverClass)) {
                if (driverClass.contains("mysql")) {
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
        tableList = new ArrayList<DbmsTable>();
        template.query(sql, new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) {
                try {
                    DbmsTable table = new DbmsTable(rs.getString("name"), rs.getString("comment"));
                    tableList.add(table);
                    tableMap.put(table.getName(), table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, conn.getCatalog());
        // 查询所有表的字段
        sql = "SELECT * FROM information_schema.COLUMNS WHERE table_schema=? AND table_name=?";
        for (DbmsTable table : tableList) {
            final List<DbmsColumn> columnList = new ArrayList<DbmsColumn>();

            template.query(sql, new Callback<ResultSet>() {
                @Override
                public void call(ResultSet rs, Object... obj) {
                    try {
                        DbmsColumn column = new DbmsColumn();
                        column.setName(rs.getString("column_name"));
                        column.setComment(rs.getString("column_comment"));
                        column.setDataType(rs.getString("data_type"));
                        column.setDefaultValue(rs.getString("column_default"));
                        column.setMaxLength(rs.getInt("character_maximum_length"));
                        column.setNullable("YES".equalsIgnoreCase(rs.getString("is_nullable")));
                        columnList.add(column);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, conn.getCatalog(), table.getName());

            table.setColumnList(columnList);
        }
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

    public Connection getConn() {
        try {
            Class.forName(getDriverClass());
            return  DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*public String getTableSchema() {
        return url.substring(url.indexOf(""))
    }*/

    public List<DbmsTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<DbmsTable> tableList) {
        this.tableList = tableList;
    }

    public DbmsTable getTable(String tableName) {
        return tableMap.get(tableName);
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
}
