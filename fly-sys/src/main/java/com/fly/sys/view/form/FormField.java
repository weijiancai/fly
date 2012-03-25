package com.fly.sys.view.form;

/**
 * @author weijiancai
 */
public class FormField {
    private String name;
    private String displayName;
    private String displayStyle;

    public FormField(String name, String displayName, String displayStyle) {
        this.name = name;
        this.displayName = displayName;
        this.displayStyle = displayStyle;
    }

    public FormField() {
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
