package com.meteorite.dbtools.idea.common.options.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.meteorite.dbtools.idea.common.options.CompositeConfiguration;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public abstract class CompositeConfigurationEditorForm<E extends CompositeConfiguration> extends ConfigurationEditorForm<E> {
    protected CompositeConfigurationEditorForm(E configuration) {
        super(configuration);
    }

    public void applyChanges() throws ConfigurationException {
    }

    public void resetChanges() {
    }
}
