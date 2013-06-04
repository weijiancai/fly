package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import org.jetbrains.annotations.Nullable;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class DatabaseBrowserForm extends DBNFormImpl {
    private Project project;

    protected DatabaseBrowserForm(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    @Nullable
    public abstract DatabaseBrowserTree getBrowserTree();

    public abstract void selectElement(BrowserTreeNode treeNode, boolean requestFocus);

    public abstract void updateTree();

    public abstract void rebuild();

    @Override
    public void dispose() {
        project = null;
    }
}
