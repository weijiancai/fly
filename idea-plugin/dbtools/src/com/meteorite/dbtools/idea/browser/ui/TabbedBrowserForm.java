package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class TabbedBrowserForm extends DatabaseBrowserForm {
    protected TabbedBrowserForm(Project project) {
        super(project);
    }

    @Nullable
    @Override
    public DatabaseBrowserTree getBrowserTree() {
        return null;
    }

    @Override
    public void selectElement(BrowserTreeNode treeNode, boolean requestFocus) {
    }

    @Override
    public void updateTree() {
    }

    @Override
    public void rebuild() {
    }

    @Override
    public JComponent getComponent() {
        return null;
    }
}
