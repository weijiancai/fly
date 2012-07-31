package com.fly.sys.vo;

import com.fly.sys.clazz.ClassTable;
import com.fly.sys.clazz.TableField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Table VO对象
 *
 * @author weijiancai
 * @since 1.0.0
 */
public class TableVO {
    private String id;
    private String name;
    private int colWidth;
    private String sql;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;

    private List<TableFieldVO> tableFieldList;

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

    public List<TableFieldVO> getTableFieldList() {
        return tableFieldList;
    }

    public void setTableFieldList(List<TableFieldVO> tableFieldList) {
        this.tableFieldList = tableFieldList;
    }

    public static TableVO getInstance(ClassTable classTable) {
        TableVO vo = new TableVO();
        vo.setColWidth(classTable.getColWidth());
        vo.setId(classTable.getId());
        vo.setInputDate(classTable.getInputDate());
        vo.setName(classTable.getName());
        vo.setSortNum(classTable.getSortNum());
        vo.setSql(classTable.getSql());
        vo.setValid(classTable.isValid());

        List<TableFieldVO> fieldList = new ArrayList<TableFieldVO>();
        for (TableField field : classTable.getTableFieldList()) {
            fieldList.add(TableFieldVO.getInstance(field));
        }
        vo.setTableFieldList(fieldList);

        return vo;
    }
}
