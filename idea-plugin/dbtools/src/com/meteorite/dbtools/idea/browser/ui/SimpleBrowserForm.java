package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeModel;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import com.meteorite.dbtools.idea.browser.model.SimpleBrowserTreeModel;
import com.meteorite.dbtools.idea.connection.ConnectionManager;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class SimpleBrowserForm extends DatabaseBrowserForm {
    protected SimpleBrowserForm(Project project) {
        this(new SimpleBrowserTreeModel(project, ConnectionManager.getInstance(project).getConnectionBundles()));
    }

    public SimpleBrowserForm(BrowserTreeModel treeModel) {
        super(treeModel.getProject());
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
