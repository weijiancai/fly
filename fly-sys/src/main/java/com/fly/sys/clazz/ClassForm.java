package com.fly.sys.clazz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class ClassForm implements Serializable {
    private String id;
    private String name;
    private String formType;
    private int colCount;
    private int colWidth;
    private int labelGap;
    private int fieldGap;
    private int hgap;
    private int vgap;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private ClassDefine classDefine;
    private List<FormField> fieldList;

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

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
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

    public int getHgap() {
        return hgap;
    }

    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    public int getVgap() {
        return vgap;
    }

    public void setVgap(int vgap) {
        this.vgap = vgap;
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

    public List<FormField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FormField> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ClassForm");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", colCount=").append(colCount);
        sb.append(", colWidth=").append(colWidth);
        sb.append(", labelGap=").append(labelGap);
        sb.append(", fieldGap=").append(fieldGap);
        sb.append('}');
        return sb.toString();
    }
}
