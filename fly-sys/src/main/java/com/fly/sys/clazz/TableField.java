package com.fly.sys.clazz;

import java.util.Date;

/**
 * @author weijiancai
 */
public class TableField {
    private String id;
    private String displayName;
    private String displayStyle;
    private boolean isDisplay;
    private int colWidth;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ClassTable classTable;
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

    public String getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(String displayStyle) {
        this.displayStyle = displayStyle;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
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

    public ClassTable getClassTable() {
        return classTable;
    }

    public void setClassTable(ClassTable classTable) {
        this.classTable = classTable;
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
}
