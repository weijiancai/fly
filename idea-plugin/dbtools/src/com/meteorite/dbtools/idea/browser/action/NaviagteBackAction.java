package com.meteorite.dbtools.idea.browser.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.DatabaseBrowserManager;
import com.meteorite.dbtools.idea.common.Icons;
import com.meteorite.dbtools.idea.common.util.ActionUtil;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class NaviagteBackAction extends DumbAwareAction {
    public NaviagteBackAction() {
        super("Back", null, Icons.BROWSER_BACK);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = ActionUtil.getProject(e);
        DatabaseBrowserManager browserManager = DatabaseBrowserManager.getInstance(project);

    }
}
