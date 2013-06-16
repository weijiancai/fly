package com.meteorite.dbtools.idea.browser.model;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.Disposable;
import com.meteorite.dbtools.idea.browser.ui.ToolTipProvider;
import com.meteorite.dbtools.idea.connection.GenericDatabaseElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface BrowserTreeNode extends NavigationItem, ItemPresentation, ToolTipProvider, Disposable, GenericDatabaseElement {
    List<BrowserTreeNode> EMPTY_LIST = new ArrayList<BrowserTreeNode>();

    void initTreeElement();

    boolean canExpand();

    int getTreeDepth();

    boolean isTreeStructureLoaded();

    BrowserTreeNode getTreeChild(int index);

    BrowserTreeNode getTreeParent();

    List<? extends BrowserTreeNode> getTreeChildren();

    void rebuildTreeChildren();

    int getTreeChildCount();

    boolean isLeafTreeElement();

    int getIndexOfTreeChild(BrowserTreeNode child);

    Icon getIcon(int flags);

    String getPresentableText();

    String getPresentableTextDedails();

    String getPresentableTextConditionalDetail();

    boolean isDisposed();
}
