package com.meteorite.dbtools.idea.options;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.meteorite.dbtools.idea.browser.options.DatabaseBrowserSettings;
import com.meteorite.dbtools.idea.common.Icons;
import com.meteorite.dbtools.idea.common.options.CompositeProjectConfiguration;
import com.meteorite.dbtools.idea.common.options.Configuration;
import com.meteorite.dbtools.idea.connection.GlobalConnectionSettings;
import com.meteorite.dbtools.idea.options.ui.GlobalProjectSettingsEditorForm;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class GlobalProjectSettings extends CompositeProjectConfiguration<GlobalProjectSettingsEditorForm> implements ProjectComponent, JDOMExternalizable, SearchableConfigurable.Parent {
    private DatabaseBrowserSettings browserSettings;
    private GlobalConnectionSettings connectionSettings;

    private GlobalProjectSettings(Project project) {
        super(project);
        browserSettings = new DatabaseBrowserSettings(project);
        connectionSettings = new GlobalConnectionSettings(project);
    }

    public static GlobalProjectSettings getInstance(Project project) {
        return project.getComponent(GlobalProjectSettings.class);
    }

    public JComponent createCustomComponent() {
        return super.createComponent();
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    public boolean hasOwnContent() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public Configurable[] getConfigurables() {
        return new Configurable[0];
    }

    @NotNull
    @Override
    public String getId() {
        return "DBTools.Project.Settings";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Database Tools";
    }

    @Nullable
    public Icon getIcon() {
        return Icons.DATABASE_NAVIGATOR;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        GlobalProjectSettingsEditorForm settingsEditor = getSettingsEditor();
        if (settingsEditor == null) {
            return super.getHelpTopic();
        } else {
            Configuration selectedConfiguration = settingsEditor.getActiveSettings();
            return selectedConfiguration.getHelpTopic();
        }
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
    }

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "DBTools.Project.Settings";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return null;
    }

    @Override
    protected Configuration[] createConfigurations() {
        return new Configuration[]{
                browserSettings,
                connectionSettings
        };
    }

    @Override
    public void disposeUIResources() {
    }

    @Override
    protected GlobalProjectSettingsEditorForm createConfigurationEditor() {
        return null;
    }

    @Override
    public void readConfiguration(Element element) throws InvalidDataException {
    }

    @Override
    public void writeConfiguration(Element element) throws WriteExternalException {
    }

    public DatabaseBrowserSettings getBrowserSettings() {
        return browserSettings;
    }

    public GlobalConnectionSettings getConnectionSettings() {
        return connectionSettings;
    }
}
