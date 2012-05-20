package com.fly.sys.module;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ModuleTpl {
    private String id;
    private String name;
    private String displayName;
    private String formId;
    private String tableId;
    private String tplDesc;

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

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTplDesc() {
        return tplDesc;
    }

    public void setTplDesc(String tplDesc) {
        this.tplDesc = tplDesc;
    }
}
