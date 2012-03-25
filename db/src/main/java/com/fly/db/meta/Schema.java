package com.fly.db.meta;

import java.util.Date;

/**
 * @author weijiancai
 */
public class Schema {
    private String id;
    private String dbmsId;
    private String name;
    private String alias;
    private String version;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

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
}
