package com.fly.sys.clazz;

import com.fly.sys.dict.DisplayStyle;
import com.fly.sys.dict.QueryMode;

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
    private DisplayStyle displayStyle;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private QueryMode queryMode;
    private boolean isReadonly;
    private boolean isRequired;
    private int dataType;

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

    public DisplayStyle getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(int displayStyle) {
        if (displayStyle == 0) {
            this.displayStyle = DisplayStyle.TEXT_FIELD;
        } else if (displayStyle == 1) {
            this.displayStyle = DisplayStyle.TEXT_AREA;
        } else if (displayStyle == 2) {
            this.displayStyle = DisplayStyle.PASSWORD;
        } else if (displayStyle == 3) {
            this.displayStyle = DisplayStyle.COMBO_BOX;
        }
    }

    public void setDisplayStyle(DisplayStyle displayStyle) {
        this.displayStyle = displayStyle;
    }

    public QueryMode getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(QueryMode queryMode) {
        this.queryMode = queryMode;
    }

    public void setQueryMode(int queryMode) {
        this.queryMode = QueryMode.get(queryMode);
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

    public boolean isReadonly() {
        return isReadonly;
    }

    public void setReadonly(boolean readonly) {
        isReadonly = readonly;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
