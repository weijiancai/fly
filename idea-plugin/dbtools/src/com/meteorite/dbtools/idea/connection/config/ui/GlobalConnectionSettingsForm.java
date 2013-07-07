package com.meteorite.dbtools.idea.connection.config.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.meteorite.dbtools.idea.common.options.Configuration;
import com.meteorite.dbtools.idea.common.options.ui.ConfigurationEditorForm;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class GlobalConnectionSettingsForm extends ConfigurationEditorForm {
    public GlobalConnectionSettingsForm(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void applyChanges() throws ConfigurationException {
    }

    @Override
    public void resetChanges() {
    }
}
