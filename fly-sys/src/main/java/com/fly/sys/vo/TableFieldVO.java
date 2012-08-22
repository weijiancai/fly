package com.fly.sys.vo;

import com.fly.sys.clazz.TableField;

import java.util.Date;

/**
 * Table字段VO对象
 *
 * @author weijiancai
 * @since 1.0.0
 */
public class TableFieldVO {
    private String id;
    private String name;
    private String displayName;
    private String displayStyle;
    private String align;
    private boolean isDisplay;
    private int colWidth;
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

    public String getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(String displayStyle) {
        this.displayStyle = displayStyle;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
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

    public static TableFieldVO getInstance(TableField field) {
        TableFieldVO vo = new TableFieldVO();
        vo.setColWidth(field.getColWidth());
        vo.setDisplay(field.isDisplay());
        vo.setDisplayName(field.getDisplayName());
        vo.setDisplayStyle(field.getDisplayStyle());
        vo.setId(field.getId());
        vo.setName(field.getClassField().getName().toLowerCase());
        vo.setInputDate(field.getInputDate());
        vo.setSortNum(field.getSortNum());
        vo.setValid(field.isValid());
        vo.setAlign(field.getAlign());

        return vo;
    }
}
