package com.fly.sys.db.meta;

import com.fly.sys.db.PO;
import com.fly.sys.util.UUIDUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class Schema implements PO {
    private String id;
    private String dbmsId;
    private String name;
    private String alias;
    private String version;
    private boolean isValid;
    private boolean isDefault;
    private Date inputDate;
    private int sortNum;
    private String url;
    private String userName;
    private String password;
    
    private DbmsDef dbms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDbmsId() {
        return dbmsId;
    }

    public void setDbmsId(String dbmsId) {
        this.dbmsId = dbmsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
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

    public DbmsDef getDbms() {
        return dbms;
    }

    public void setDbms(DbmsDef dbms) {
        this.dbms = dbms;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Schema");
        sb.append("{id='").append(id).append('\'');
        sb.append(", dbmsId='").append(dbmsId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", inputDate=").append(inputDate);
        sb.append(", sortNum=").append(sortNum);
        sb.append('}');
        return sb.toString();
    }

    public Connection getConn() {
        try {
            Class.forName(dbms.getDriverClass());
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
    public Map<String, Object> getPoMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", UUIDUtil.getUUID());
        map.put("dbms_id", getDbmsId());
        map.put("name", getName());
        map.put("alias", getAlias());
        map.put("version", getVersion());
        map.put("url", getUrl());
        map.put("is_default", isDefault() ? "T" : "F");
        map.put("user_name", getUserName());
        map.put("password", getPassword());
        map.put("is_valid", isValid() ? "T" : "F");
        map.put("input_date", new Date());
        map.put("sort_num", getSortNum());

        return map;
    }
}
