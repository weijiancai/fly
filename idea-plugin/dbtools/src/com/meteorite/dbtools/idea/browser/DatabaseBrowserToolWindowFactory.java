package com.meteorite.dbtools.idea.browser;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactoryImpl;
import com.meteorite.dbtools.idea.browser.ui.BrowserToolWindowForm;
import com.meteorite.dbtools.idea.common.Icons;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class DatabaseBrowserToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        BrowserToolWindowForm toolWindowForm = DatabaseBrowserManager.getInstance(project).getToolWindowForm();
        ContentFactory contentFactory = new ContentFactoryImpl();
        Content content = contentFactory.createContent(toolWindowForm.getComponent(), null, false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.setIcon(Icons.WINDOW_DATABASE_BROWSER);
    }
}
