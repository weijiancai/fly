package com.meteorite.dbtools.idea.browser.model;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.Disposable;
import com.meteorite.dbtools.idea.browser.ui.TooltipProvider;
import com.meteorite.dbtools.idea.connection.GenericDatabaseElement;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class BrowserTreeNode extends NavigationItem, ItemPresentation, TooltipProvider, Disposable, GenericDatabaseElement {
}
