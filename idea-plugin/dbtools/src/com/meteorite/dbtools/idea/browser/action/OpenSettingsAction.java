package com.meteorite.dbtools.idea.browser.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.options.DatabaseBrowserSettings;
import com.meteorite.dbtools.idea.common.Icons;
import com.meteorite.dbtools.idea.common.util.ActionUtil;
import com.meteorite.dbtools.idea.options.ui.GlobalProjectSettingsDialog;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class OpenSettingsAction extends AnAction {
    public OpenSettingsAction() {
        super("Settings", null, Icons.ACTION_SETTINGS);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = ActionUtil.getProject(e);
        GlobalProjectSettingsDialog globalSettingsDialog = new GlobalProjectSettingsDialog(project);
        DatabaseBrowserSettings databaseBrowserSettings = DatabaseBrowserSettings.getInstance(project);
        globalSettingsDialog.focusSettings(databaseBrowserSettings);
        globalSettingsDialog.show();
    }

    public void update(AnActionEvent e) {
        Presentation presentation = e.getPresentation();
        presentation.setText("Settings");
    }
}
