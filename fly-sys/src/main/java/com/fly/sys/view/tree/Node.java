package com.fly.sys.view.tree;

import java.util.List;

/**
 * 树形视图中的节点信息
 * 
 * @author weijiancai
 */
public class Node {
    private String id;
    private String label;
    private List<Node> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
