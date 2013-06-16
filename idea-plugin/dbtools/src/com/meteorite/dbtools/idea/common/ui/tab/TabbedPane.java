package com.meteorite.dbtools.idea.common.ui.tab;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBTabsImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class TabbedPane extends JBTabsImpl {
    public TabbedPane(@NotNull Project project) {
        super(project);
    }

    public void select(JComponent component, boolean requestFocus) {
        TabInfo tabInfo = findInfo(component);
        if (tabInfo != null) {
            select(tabInfo, requestFocus);
        }
    }

    @Override
    public void dispose() {
        for (TabInfo tabInfo : getTabs()) {
            Object object = tabInfo.getObject();
            if (object instanceof Disposable) {
                Disposable disposable = (Disposable) object;
                disposable.dispose();
            }
        }
        super.dispose();
    }
}
