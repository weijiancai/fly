package com.meteorite.dbtools.idea.connection.config.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.meteorite.dbtools.idea.common.options.ui.ConfigurationEditorForm;
import com.meteorite.dbtools.idea.connection.ConnectionBundle;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ConnectionBundleSettingsForm extends ConfigurationEditorForm<ConnectionBundle> implements ListSelectionListener {
    public ConnectionBundleSettingsForm(ConnectionBundle configuration) {
        super(configuration);
    }

    @Override
    public void applyChanges() throws ConfigurationException {
    }

    @Override
    public void resetChanges() {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
