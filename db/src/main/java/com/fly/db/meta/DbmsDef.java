package com.fly.db.meta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class DbmsDef {
    private String id;
    private String name;
    private String type;
    private String host;
    private String userName;
    private String password;
    private String port;
    private String driverClass;
    private String url;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private List<Schema> schemaList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public List<Schema> getSchemaList() {
        return schemaList;
    }

    public void setSchemaList(List<Schema> schemaList) {
        this.schemaList = schemaList;
    }

    public Connection getConn() {
        try {
            Class.forName(getDriverClass());
            Connection conn = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DbmsDef");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", driverClass='").append(driverClass).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", inputDate=").append(inputDate);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", schemaList=").append(schemaList);
        sb.append('}');
        return sb.toString();
    }
}
