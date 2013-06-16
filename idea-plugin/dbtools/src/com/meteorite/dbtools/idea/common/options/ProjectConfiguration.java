package com.meteorite.dbtools.idea.common.options;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.common.options.ui.ConfigurationEditorForm;
import com.meteorite.dbtools.idea.options.GlobalProjectSettings;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public abstract class ProjectConfiguration<T extends ConfigurationEditorForm> extends Configuration<T> {
    private Project project;

    protected ProjectConfiguration(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    protected static GlobalProjectSettings getGlobalProjectSettings(Project project) {
        return GlobalProjectSettings.getInstance(project);
    }
}
