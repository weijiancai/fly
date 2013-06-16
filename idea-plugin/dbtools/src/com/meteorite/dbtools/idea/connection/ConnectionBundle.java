package com.meteorite.dbtools.idea.connection;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import com.meteorite.dbtools.idea.common.content.DynamicContent;
import com.meteorite.dbtools.idea.common.content.DynamicContentType;
import com.meteorite.dbtools.idea.common.options.Configuration;
import com.meteorite.dbtools.idea.connection.config.ui.ConnectionBundleSettingsForm;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;
import sun.jdbc.odbc.ee.ConnectionHandler;

import javax.swing.*;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ConnectionBundle extends Configuration<ConnectionBundleSettingsForm> implements Comparable, BrowserTreeNode, JDOMExternalizable, Disposable {
    private Project project;

    protected ConnectionBundle(Project project) {
        this.project = project;
    }

    @Override
    public void initTreeElement() {
    }

    @Override
    public boolean canExpand() {
        return false;
    }

    @Override
    public int getTreeDepth() {
        return 0;
    }

    @Override
    public boolean isTreeStructureLoaded() {
        return false;
    }

    @Override
    public BrowserTreeNode getTreeChild(int index) {
        return null;
    }

    @Override
    public BrowserTreeNode getTreeParent() {
        return null;
    }

    @Override
    public List<? extends BrowserTreeNode> getTreeChildren() {
        return null;
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
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    protected ConnectionBundleSettingsForm createConfigurationEditor() {
        return null;
    }

    @Override
    public void dispose() {
    }

    @Override
    public Project getProject() {
        return null;
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

    @Override
    public void readExternal(Element element) throws InvalidDataException {
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
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
    public void readConfiguration(Element element) throws InvalidDataException {
    }

    @Override
    public void writeConfiguration(Element element) throws WriteExternalException {
    }

    @Override
    public String getToolTip() {
        return null;
    }
}
