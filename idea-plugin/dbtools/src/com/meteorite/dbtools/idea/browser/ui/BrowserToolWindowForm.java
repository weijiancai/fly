package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.DatabaseBrowserManager;
import com.meteorite.dbtools.idea.browser.options.BrowserDisplayMode;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BrowserToolWindowForm {
    private JPanel mainPanel;
    private JPanel actionsPanel;
    private JPanel browserPanel;

    private Project project;
    private BrowserDisplayMode displayMode;

    public BrowserToolWindowForm(Project project) {
        this.project = project;

        DatabaseBrowserManager browserManager = DatabaseBrowserManager.getInstance(project);
        displayMode = BrowserDisplayMode.SIMPLE; // TODO Setting

    }
}
