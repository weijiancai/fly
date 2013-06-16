package com.meteorite.dbtools.idea.common.ui.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.meteorite.dbtools.idea.common.Constants;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class DBNDialog extends DialogWrapper {
    private Project project;

    protected DBNDialog(Project project, String title, boolean canBeParent) {
        super(project, canBeParent);
        setTitle(Constants.DBN_TITLE_PREFIX + title);
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
