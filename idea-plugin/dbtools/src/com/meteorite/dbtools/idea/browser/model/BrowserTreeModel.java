package com.meteorite.dbtools.idea.browser.model;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BrowserTreeModel implements TreeModel, Disposable {
    private Project project;
    private BrowserTreeNode root;

    protected BrowserTreeModel(BrowserTreeNode root) {
        this.root = root;
    }

    @Override
    public void dispose() {
    }

    @Override
    public Object getRoot() {
        return null;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }

    public Project getProject() {
        return root.getProject();
    }
}
