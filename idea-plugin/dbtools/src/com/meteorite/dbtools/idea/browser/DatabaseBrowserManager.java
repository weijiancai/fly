package com.meteorite.dbtools.idea.browser;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.ui.BrowserToolWindowForm;
import com.meteorite.dbtools.idea.common.AbstractProjectComponent;
import org.jetbrains.annotations.NotNull;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class DatabaseBrowserManager extends AbstractProjectComponent {
    public static final String TOOL_WINDOW_ID = "DB - Browser";
    private BrowserToolWindowForm toolWindowForm;

    private DatabaseBrowserManager(Project project) {
        super(project);
    }

    @Override
    public String toString() {
        return "DB - Browser";
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "DBTools.Project.DatabaseBrowserManager";
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {
//        DisposeUtil.dispose();
        super.disposeComponent();
    }

    public static DatabaseBrowserManager getInstance(Project project) {
        return project.getComponent(DatabaseBrowserManager.class);
    }

    public BrowserToolWindowForm getToolWindowForm() {
        if (toolWindowForm == null) {
            toolWindowForm = new BrowserToolWindowForm(getProject());
        }
        return toolWindowForm;
    }
}
