package com.fly.sys.db.meta;

import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class Table {
    private String id;
    private String schemaId;
    private String name;
    private String alias;
    private String comment;
    private String displayName;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    List<Column> columnList;

    public Table(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Table");
        sb.append("{id='").append(id).append('\'');
        sb.append(", schemaId='").append(schemaId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", inputDate=").append(inputDate);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", columnList=").append(columnList);
        sb.append('}');
        return sb.toString();
    }
}
