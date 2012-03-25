package com.fly.sys.view.table;

/**
 * @author weijiancai
 */
public class ColAttr {
    private String name;
    private String displayName;
    private String displayStyle;

    public ColAttr(String name, String displayName, String displayStyle) {
        this.name = name;
        this.displayName = displayName;
        this.displayStyle = displayStyle;
    }

    public ColAttr() {
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
}
