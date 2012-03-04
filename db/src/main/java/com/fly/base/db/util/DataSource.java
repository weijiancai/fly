package com.fly.base.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author weijiancai
 */
public class DataSource {
    private String name;
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public DataSource(String name, String driverClass, String url, String username, String password) {
        this.name = name;
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public DataSource() {
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

    @Override
    public String toString() {
        return "DataSource{" +
                "name='" + name + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
