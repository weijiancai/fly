package com.fly.sys.db.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class DbmsDefine implements Serializable {
    private String id;
    private String name;
    private String type;
    private String host;
    private String port;
    private String driverClass;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private List<DbmsSchema> schemaList;
    private Map<String, Schema> schemaMap;

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

    public List<DbmsSchema> getSchemaList() {
        return schemaList == null ? new ArrayList<DbmsSchema>() : schemaList;
    }

    public void setSchemaList(List<DbmsSchema> schemaList) {
        this.schemaList = schemaList;
    }

    public Map<String, Schema> getSchemaMap() {
        return schemaMap;
    }

    public void setSchemaMap(Map<String, Schema> schemaMap) {
        this.schemaMap = schemaMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DbmsDefine");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", driverClass='").append(driverClass).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", inputDate=").append(inputDate);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", schemaList=").append(schemaList);
        sb.append(", schemaMap=").append(schemaMap);
        sb.append('}');
        return sb.toString();
    }

    public String getNameKey() {
        return getName();
    }
}
