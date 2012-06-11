package com.fly.sys.clazz;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weijiancai
 */
public class FormField implements Serializable {
    private String id;
    private String displayName;
    private boolean isSingleLine;
    private boolean isDisplay;
    private int width;
    private int height;
    private String displayStyle;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ClassForm classForm;
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

    public boolean isSingleLine() {
        return isSingleLine;
    }

    public void setSingleLine(boolean singleLine) {
        isSingleLine = singleLine;
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

    public ClassForm getClassForm() {
        return classForm;
    }

    public void setClassForm(ClassForm classForm) {
        this.classForm = classForm;
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
}
