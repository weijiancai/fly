package com.meteorite.dbtools.idea.options.ui;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.options.DatabaseBrowserSettings;
import com.meteorite.dbtools.idea.common.options.Configuration;
import com.meteorite.dbtools.idea.common.ui.dialog.DBNDialog;
import com.meteorite.dbtools.idea.options.GlobalProjectSettings;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class GlobalProjectSettingsDialog extends DBNDialog {
    private JButton bApplet;
    private GlobalProjectSettings globalSettings;

    public GlobalProjectSettingsDialog(Project project) {
        super(project, "Settings", true);
        setModal(true);
        setResizable(true);

        globalSettings = GlobalProjectSettings.getInstance(project);
        init();
    }

    public String getDimensionServiceKey() {
        return "DBTools.GlobalSettings";
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return GlobalProjectSettings.getInstance(getProject()).createCustomComponent();
    }

    public void focusSettings(Configuration configuration) {
        GlobalProjectSettingsEditorForm globalSettingsEditor = globalSettings.getSettingsEditor();
        if (globalSettingsEditor != null) {
            globalSettingsEditor.focusSettingsEditor(configuration);
        }
    }
}
