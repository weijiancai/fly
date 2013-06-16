package com.meteorite.dbtools.idea.options.ui;

import com.intellij.ui.tabs.TabInfo;
import com.meteorite.dbtools.idea.common.options.Configuration;
import com.meteorite.dbtools.idea.common.options.ui.CompositeConfigurationEditorForm;
import com.meteorite.dbtools.idea.common.ui.tab.TabbedPane;
import com.meteorite.dbtools.idea.options.GlobalProjectSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class GlobalProjectSettingsEditorForm extends CompositeConfigurationEditorForm<GlobalProjectSettings> {
    private JPanel mainPanel;
    private TabbedPane configurationTabs;

    public GlobalProjectSettingsEditorForm(GlobalProjectSettings globalSettings) {
        super(globalSettings);
    }

    public void focusSettingsEditor(Configuration configuration) {
        JComponent component = configuration.getSettingsEditor().getComponent();
        if (component != null) {
            TabInfo tabInfo = getTabInfo(component);
            configurationTabs.select(tabInfo, true);
        }
    }

    private TabInfo getTabInfo(JComponent component) {
        for (TabInfo tabInfo : configurationTabs.getTabs()) {
            if (tabInfo.getComponent() == component) {
                return tabInfo;
            }
        }
        return null;
    }

    @NotNull
    public Configuration getActiveSettings() {
        TabInfo tabInfo = configurationTabs.getSelectedInfo();
        if (tabInfo != null) {
            return (Configuration) tabInfo.getObject();
        }
        return getConfiguration();
    }
}
