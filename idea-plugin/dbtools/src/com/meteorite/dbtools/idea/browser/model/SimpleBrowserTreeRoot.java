package com.meteorite.dbtools.idea.browser.model;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.common.content.DynamicContent;
import com.meteorite.dbtools.idea.common.content.DynamicContentType;
import com.meteorite.dbtools.idea.connection.ConnectionBundle;
import com.meteorite.dbtools.idea.connection.GenericDatabaseElement;
import org.jetbrains.annotations.Nullable;
import sun.jdbc.odbc.ee.ConnectionHandler;

import javax.swing.*;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class SimpleBrowserTreeRoot implements BrowserTreeNode {
    private List<ConnectionBundle> connectionBundles;
    private Project project;

    public SimpleBrowserTreeRoot(Project project, List<ConnectionBundle> connectionBundles) {
        this.project = project;
        this.connectionBundles = connectionBundles;
    }

    public Project getProject() {
        return project;
    }

    @Override
    public ConnectionHandler getConnectionHandler() {
        return null;
    }

    @Override
    public GenericDatabaseElement getUndiposedElement() {
        return null;
    }

    @Override
    public DynamicContent getDynamicContent(DynamicContentType dynamicContentType) {
        return null;
    }

    public boolean isTreeStructureLoaded() {
        return true;
    }

    @Override
    public BrowserTreeNode getTreeChild(int index) {
        return null;
    }

    public void initTreeElement() {}

    public boolean canExpand() {
        return true;
    }

    public int getTreeDepth() {
        return 0;
    }

    public BrowserTreeNode getTreeParent() {
        return null;
    }

    public List<? extends BrowserTreeNode> getTreeChildren() {
        return connectionBundles;
    }

    @Override
    public void rebuildTreeChildren() {
    }

    @Override
    public int getTreeChildCount() {
        return 0;
    }

    @Override
    public boolean isLeafTreeElement() {
        return false;
    }

    @Override
    public int getIndexOfTreeChild(BrowserTreeNode child) {
        return 0;
    }

    @Override
    public Icon getIcon(int flags) {
        return null;
    }

    @Override
    public String getPresentableText() {
        return null;
    }

    @Nullable
    @Override
    public String getLocationString() {
        return null;
    }

    @Nullable
    @Override
    public Icon getIcon(boolean unused) {
        return null;
    }

    @Override
    public String getPresentableTextDedails() {
        return null;
    }

    @Override
    public String getPresentableTextConditionalDetail() {
        return null;
    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public void dispose() {
    }

    @Nullable
    @Override
    public String getName() {
        return null;
    }

    @Nullable
    @Override
    public ItemPresentation getPresentation() {
        return null;
    }

    @Override
    public void navigate(boolean requestFocus) {
    }

    @Override
    public boolean canNavigate() {
        return false;
    }

    @Override
    public boolean canNavigateToSource() {
        return false;
    }

    @Override
    public String getToolTip() {
        return null;
    }
}
