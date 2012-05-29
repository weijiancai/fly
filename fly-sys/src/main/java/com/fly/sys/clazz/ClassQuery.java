package com.fly.sys.clazz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * table查询
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class ClassQuery implements Serializable {
    private String id;
    private String classId;
    private String name;
    private int colCount;
    private int colWidth;
    private int labelGap;
    private int fieldGap;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ClassDefine classDefine;
    private List<QueryField> queryFieldList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
    }

    public int getLabelGap() {
        return labelGap;
    }

    public void setLabelGap(int labelGap) {
        this.labelGap = labelGap;
    }

    public int getFieldGap() {
        return fieldGap;
    }

    public void setFieldGap(int fieldGap) {
        this.fieldGap = fieldGap;
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

    public ClassDefine getClassDefine() {
        return classDefine;
    }

    public void setClassDefine(ClassDefine classDefine) {
        this.classDefine = classDefine;
    }

    public List<QueryField> getQueryFieldList() {
        return queryFieldList;
    }

    public void setQueryFieldList(List<QueryField> queryFieldList) {
        this.queryFieldList = queryFieldList;
    }
}
