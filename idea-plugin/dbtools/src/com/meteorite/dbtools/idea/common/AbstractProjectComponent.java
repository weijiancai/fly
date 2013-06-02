package com.meteorite.dbtools.idea.common;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class AbstractProjectComponent implements ProjectComponent {
    private Project project;
    private boolean isDisposed = false;

    protected AbstractProjectComponent(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public boolean isDisposed() {
        return isDisposed;
    }

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
        project = null;
        isDisposed = true;
    }
}
