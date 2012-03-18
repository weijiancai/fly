package com.fly.sys.view.tree;

import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class EasyuiNode {
    private String id;
    private String text;
    private String iconCls;
    private boolean checked;
    private String state;
    private Map<String, Object> attributes;
    private List<EasyuiNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<EasyuiNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyuiNode> children) {
        this.children = children;
    }
}
