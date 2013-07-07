package com.meteorite.dbtools.idea.browser.model;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.connection.ConnectionBundle;

import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class SimpleBrowserTreeModel extends BrowserTreeModel {
    public SimpleBrowserTreeModel(Project project, List<ConnectionBundle> connectionBundles) {
        super(new SimpleBrowserTreeRoot(project, connectionBundles));
    }
}
