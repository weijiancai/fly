package com.meteorite.dbtools.idea.browser.options;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.options.ui.DatabaseBrowserSettingsForm;
import com.meteorite.dbtools.idea.common.options.CompositeProjectConfiguration;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class DatabaseBrowserSettings extends CompositeProjectConfiguration<DatabaseBrowserSettingsForm> {
    private DatabaseBrowserGeneralSettings generalSettings;

    public DatabaseBrowserSettings(Project project) {
        super(project);
        generalSettings = new DatabaseBrowserGeneralSettings(project);
    }
}
