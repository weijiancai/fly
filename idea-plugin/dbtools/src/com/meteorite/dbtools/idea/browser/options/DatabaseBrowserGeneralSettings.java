package com.meteorite.dbtools.idea.browser.options;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.meteorite.dbtools.idea.browser.ui.DatabaseBrowserGeneralSettingsForm;
import com.meteorite.dbtools.idea.common.options.ProjectConfiguration;
import org.jdom.Element;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class DatabaseBrowserGeneralSettings extends ProjectConfiguration<DatabaseBrowserGeneralSettingsForm> {
    public DatabaseBrowserGeneralSettings(Project project) {
        super(project);
    }

    @Override
    protected DatabaseBrowserGeneralSettingsForm createConfigurationEditor() {
        return null;
    }

    @Override
    public void readConfiguration(Element element) throws InvalidDataException {
    }

    @Override
    public void writeConfiguration(Element element) throws WriteExternalException {
    }
}
