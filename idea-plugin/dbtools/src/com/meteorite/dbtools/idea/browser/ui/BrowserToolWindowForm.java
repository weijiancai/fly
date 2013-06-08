package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.DatabaseBrowserManager;
import com.meteorite.dbtools.idea.browser.options.BrowserDisplayMode;
import com.meteorite.dbtools.idea.common.ui.GUIUtil;
import com.meteorite.dbtools.idea.common.util.ActionUtil;

import javax.swing.*;
import java.awt.*;

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
    private DatabaseBrowserForm browserForm;

    public BrowserToolWindowForm(Project project) {
        this.project = project;

        DatabaseBrowserManager browserManager = DatabaseBrowserManager.getInstance(project);
        displayMode = BrowserDisplayMode.SIMPLE; // TODO Setting
        initBrowserForm();

        ActionToolbar actionToolbar = ActionUtil.createActionToolbar("", true, "DBTools.ActionGroup.Browser.Controls");
        actionsPanel.add(actionToolbar.getComponent());

        GUIUtil.updateSplitterProportion(mainPanel, (float) 0.7);
    }

    private void initBrowserForm() {
        browserForm =
                displayMode == BrowserDisplayMode.TABBED ? new TabbedBrowserForm(project) :
                displayMode == BrowserDisplayMode.SIMPLE ? new SimpleBrowserForm(project) : null;

        browserPanel.removeAll();
        browserPanel.add(browserForm.getComponent(), BorderLayout.CENTER);
    }

    public JComponent getComponent() {
        return mainPanel;
    }
}
