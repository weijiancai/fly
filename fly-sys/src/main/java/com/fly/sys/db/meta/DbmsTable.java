package com.fly.sys.db.meta;

import com.fly.sys.clazz.ClassDefine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class DbmsTable implements Serializable {
    private String id;
    private String name;
    private String alias;
    private String comment;
    private String displayName;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private DbmsSchema schema;
    private List<DbmsColumn> columnList;
    private List<ClassDefine> classList;

    public DbmsTable() {
    }

    public DbmsTable(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

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

    public DbmsSchema getSchema() {
        return schema;
    }

    public void setSchema(DbmsSchema schema) {
        this.schema = schema;
    }

    public List<DbmsColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<DbmsColumn> columnList) {
        this.columnList = columnList;
    }

    public List<ClassDefine> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassDefine> classList) {
        this.classList = classList;
    }

    public void addClassDefine(ClassDefine classDefine) {
        if (classList == null) {
            classList = new ArrayList<ClassDefine>();
        }
        classList.add(classDefine);
    }

    public String getNameKey() {
        return getSchema().getNameKey() + "." + getName();
    }
}
