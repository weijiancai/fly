package com.fly.sys.db.metadata;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DBConnectionParam {
    private String driverName;
    private String url;
    private String userName;
    private String password;
    private DatabaseType databaseType;

    public DBConnectionParam() {
    }

    public DBConnectionParam(DatabaseType databaseType, String driverName, String url, String userName, String password) {
        this.databaseType = databaseType;
        this.driverName = driverName;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }
}
