package com.fly.sys.clazz;

import java.util.Date;

/**
 * @author weijiancai
 */
public class ClassTable {
    private String id;
    private String name;
    private int colWidth;
    private String sql;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private ClassDefine classDefine;

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

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
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

    public ClassDefine getClassDefine() {
        return classDefine;
    }

    public void setClassDefine(ClassDefine classDefine) {
        this.classDefine = classDefine;
    }
}
