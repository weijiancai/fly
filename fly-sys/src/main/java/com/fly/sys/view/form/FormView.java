package com.fly.sys.view.form;

import java.util.List;

/**
 * @author weijiancai
 */
public class FormView {
    private String classDef;
    private String name;
    private int colCount;
    private int colWidth;
    private int labelGap;
    private int fieldGap;

    private List<FormField> fieldList;

    public String getClassDef() {
        return classDef;
    }

    public void setClassDef(String classDef) {
        this.classDef = classDef;
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

    public List<FormField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FormField> fieldList) {
        this.fieldList = fieldList;
    }
}
