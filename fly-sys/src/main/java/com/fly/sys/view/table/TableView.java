package com.fly.sys.view.table;

import java.util.List;
import java.util.Map;

/**
 * 表格视图
 *
 * @author weijiancai
 */
public class TableView {
    private String name;
    private int colWidth;
    private String classDef;

    private List<ColAttr> colAttr;
    private List<Map<String, Object>> colData;

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

    public List<ColAttr> getColAttr() {
        return colAttr;
    }

    public void setColAttr(List<ColAttr> colAttr) {
        this.colAttr = colAttr;
    }

    public List<Map<String, Object>> getColData() {
        return colData;
    }

    public void setColData(List<Map<String, Object>> colData) {
        this.colData = colData;
    }

    public String getClassDef() {
        return classDef;
    }

    public void setClassDef(String classDef) {
        this.classDef = classDef;
    }
}
