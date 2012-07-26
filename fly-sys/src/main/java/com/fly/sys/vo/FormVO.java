package com.fly.sys.vo;

import com.fly.sys.clazz.ClassForm;
import com.fly.sys.clazz.FormField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class FormVO {
    private String id;
    private String name;
    private int colCount;
    private int colWidth;
    private int labelGap;
    private int fieldGap;
    private int hgap;
    private int vgap;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private List<FormFieldVO> fieldList;

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

    public List<FormFieldVO> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FormFieldVO> fieldList) {
        this.fieldList = fieldList;
    }

    public static FormVO getInstance(ClassForm form) {
        FormVO vo = new FormVO();

        vo.setId(form.getId());
        vo.setColCount(form.getColCount());
        vo.setColWidth(form.getColWidth());
        vo.setFieldGap(form.getFieldGap());
        vo.setHgap(form.getHgap());
        vo.setInputDate(form.getInputDate());
        vo.setLabelGap(form.getLabelGap());
        vo.setName(form.getName());
        vo.setSortNum(form.getSortNum());
        vo.setValid(form.isValid());
        vo.setVgap(form.getVgap());

        List<FormFieldVO> fieldList = new ArrayList<FormFieldVO>();
        for (FormField field : form.getFieldList()) {
            fieldList.add(FormFieldVO.getInstance(field));
        }
        vo.setFieldList(fieldList);

        return vo;
    }
}
