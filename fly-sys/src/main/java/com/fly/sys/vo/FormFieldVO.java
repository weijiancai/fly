package com.fly.sys.vo;

import com.fly.sys.clazz.FormField;

import java.util.Date;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class FormFieldVO {
    private String id;
    private String name;
    private String displayName;
    private boolean isSingleLine;
    private boolean isDisplay;
    private int width;
    private int height;
    private int displayStyle;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isSingleLine() {
        return isSingleLine;
    }

    public void setSingleLine(boolean singleLine) {
        isSingleLine = singleLine;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
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

    public int getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(int displayStyle) {
        this.displayStyle = displayStyle;
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

    public static FormFieldVO getInstance(FormField field) {
        FormFieldVO vo = new FormFieldVO();

        vo.setDisplay(field.isDisplay());
        vo.setName(field.getClassField().getName());
        vo.setDisplayName(field.getDisplayName());
        if (field.getDisplayStyle() == null) {
            vo.setDisplayStyle(0);
        } else {
            vo.setDisplayStyle(field.getDisplayStyle().ordinal());
        }
        vo.setHeight(field.getHeight());
        vo.setId(field.getId());
        vo.setInputDate(field.getInputDate());
        vo.setSingleLine(field.isSingleLine());
        vo.setSortNum(field.getSortNum());
        vo.setValid(field.isValid());
        vo.setWidth(field.getWidth());

        return vo;
    }
}
