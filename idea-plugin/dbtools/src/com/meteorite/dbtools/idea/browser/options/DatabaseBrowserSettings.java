package com.meteorite.dbtools.idea.browser.options;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.options.ui.DatabaseBrowserSettingsForm;
import com.meteorite.dbtools.idea.common.options.CompositeProjectConfiguration;
import com.meteorite.dbtools.idea.common.options.Configuration;

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

    @Override
    protected Configuration[] createConfigurations() {
        return new Configuration[0];
    }

    @Override
    protected DatabaseBrowserSettingsForm createConfigurationEditor() {
        return null;
    }

    public static DatabaseBrowserSettings getInstance(Project project) {
        return getGlobalProjectSettings(project).getBrowserSettings();
    }
}
