package com.fly.sys.db.meta;

import java.util.Date;

/**
 * @author weijiancai
 */
public class Column {
    private String id;
    private String tableId;
    private String name;
    private String alias;
    private String displayName;
    private String categoryId;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;
    private String defaultValue;
    private boolean isNull;
    private boolean isPk;
    private boolean isFk;
    private String fkTableId;
    private String dataType;
    private int maxLength;
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Column");
        sb.append("{id='").append(id).append('\'');
        sb.append(", tableId='").append(tableId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", inputDate=").append(inputDate);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", defaultValue='").append(defaultValue).append('\'');
        sb.append(", isNull=").append(isNull);
        sb.append(", dataType='").append(dataType).append('\'');
        sb.append(", maxLength=").append(maxLength);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
