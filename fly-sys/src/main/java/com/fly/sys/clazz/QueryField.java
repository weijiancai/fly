package com.fly.sys.clazz;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weijiancai
 */
public class QueryField implements Serializable {
    private String id;
    private String queryId;
    private String fieldId;
    private String operator;
    private int width;
    private int height;
    private String displayName;
    private String displayStyle;
    private boolean isDisplay;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ClassQuery classQuery;
    private ClassField classField;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(String displayStyle) {
        this.displayStyle = displayStyle;
    }

    public ClassField getClassField() {
        return classField;
    }

    public void setClassField(ClassField classField) {
        this.classField = classField;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public ClassQuery getClassQuery() {
        return classQuery;
    }

    public void setClassQuery(ClassQuery classQuery) {
        this.classQuery = classQuery;
    }
}
