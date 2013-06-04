package com.meteorite.dbtools.idea.connection;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.meteorite.dbtools.idea.common.AbstractProjectComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ConnectionManager extends AbstractProjectComponent implements ProjectManagerListener {
    private List<ConnectionBundle> connectionBundles = new ArrayList<ConnectionBundle>();

    private ConnectionManager(Project project) {
        super(project);
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.addProjectManagerListener(this);
    }

    public static ConnectionManager getInstance(Project project) {
        return project.getComponent(ConnectionManager.class);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "DBTools.Project.DatabaseConnectionManager";
    }

    @Override
    public void projectOpened(Project project) {
    }

    @Override
    public boolean canCloseProject(Project project) {
        return false;
    }

    @Override
    public void projectClosed(Project project) {
    }

    @Override
    public void projectClosing(Project project) {
    }

    public List<ConnectionBundle> getConnectionBundles() {
        return connectionBundles;
    }
}
