package com.meteorite.dbtools.idea.connection;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.meteorite.dbtools.idea.common.options.ProjectConfiguration;
import com.meteorite.dbtools.idea.connection.config.ui.GlobalConnectionSettingsForm;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class GlobalConnectionSettings extends ProjectConfiguration<GlobalConnectionSettingsForm> {
    public GlobalConnectionSettings(Project project) {
        super(project);
    }

    public static GlobalConnectionSettings getInstance(Project project) {
        return getGlobalProjectSettings(project).getConnectionSettings();
    }

    @NotNull
    @Override
    public String getId() {
        return "DBTools.Project.ConnectionSettings";
    }

    public String getDisplayName() {
        return "Connections";
    }

    public String getHelpTopic() {
        return "connectionBundle";
    }

    public GlobalConnectionSettingsForm createConfigurationEditor() {
//        return new GlobalConnectionSettingsForm(this);
        return null;
    }

    public boolean isModified() {
        /*ProjectConnectionBundle projectConnectionManager = ProjectConnectionBundle.getInstance(getProject());
        if (projectConnectionManager.isModified()) return true;

        Module[] modules =  ModuleManager.getInstance(getProject()).getModules();
        for (Module module : modules) {
            ModuleConnectionBundle moduleConnectionManager = ModuleConnectionBundle.getInstance(module);
            if (moduleConnectionManager.isModified()) return true;
        }*/
        return false;
    }

    @Override
    public void readConfiguration(Element element) throws InvalidDataException {
    }

    @Override
    public void writeConfiguration(Element element) throws WriteExternalException {
    }
}
