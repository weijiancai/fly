package com.meteorite.dbtools.idea.browser.options;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.ui.DatabaseBrowserGeneralSettingsForm;
import com.meteorite.dbtools.idea.common.options.ProjectConfiguration;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class DatabaseBrowserGeneralSettings extends ProjectConfiguration<DatabaseBrowserGeneralSettingsForm> {
    public DatabaseBrowserGeneralSettings(Project project) {
        super(project);
    }
}
