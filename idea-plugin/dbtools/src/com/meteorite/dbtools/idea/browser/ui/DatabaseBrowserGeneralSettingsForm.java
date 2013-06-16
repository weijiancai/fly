package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.meteorite.dbtools.idea.browser.options.DatabaseBrowserGeneralSettings;
import com.meteorite.dbtools.idea.common.options.ui.ConfigurationEditorForm;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class DatabaseBrowserGeneralSettingsForm extends ConfigurationEditorForm<DatabaseBrowserGeneralSettings> {
    public DatabaseBrowserGeneralSettingsForm(DatabaseBrowserGeneralSettings configuration) {
        super(configuration);
    }

    @Override
    public void applyChanges() throws ConfigurationException {
    }

    @Override
    public void resetChanges() {
    }
}
