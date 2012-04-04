package com.fly.sys.db.meta;

import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class DbmsSchema {
    private String id;
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

    private DbmsDefine dbms;
    private List<DbmsTable> tableList;

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

    public DbmsDefine getDbms() {
        return dbms;
    }

    public void setDbms(DbmsDefine dbms) {
        this.dbms = dbms;
    }

    public List<DbmsTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<DbmsTable> tableList) {
        this.tableList = tableList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DbmsSchema");
        sb.append("{id='").append(id).append("'\n");
        sb.append(", name='").append(name).append("'\n");
        sb.append(", alias='").append(alias).append("'\n");
        sb.append(", version='").append(version).append("'\n");
        sb.append(", isValid=").append(isValid);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", inputDate=").append(inputDate);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", url='").append(url).append("'\n");
        sb.append(", userName='").append(userName).append("'\n");
        sb.append(", password='").append(password).append("'\n");
        sb.append(", tableList=").append(tableList);
        sb.append('}');
        return sb.toString();
    }

    public String getNameKey() {
        return getDbms().getNameKey() + "." + getName();
    }
}
