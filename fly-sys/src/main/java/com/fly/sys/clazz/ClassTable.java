package com.fly.sys.clazz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class ClassTable implements Serializable {
    private String id;
    private String name;
    private int colWidth;
    private String sql;
    private String joinSql;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private ClassDefine classDefine;
    private List<TableField> tableFieldList;

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

    public String getJoinSql() {
        return joinSql;
    }

    public void setJoinSql(String joinSql) {
        this.joinSql = joinSql;
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

    public List<TableField> getTableFieldList() {
        return tableFieldList;
    }

    public void setTableFieldList(List<TableField> tableFieldList) {
        this.tableFieldList = tableFieldList;
    }
}
