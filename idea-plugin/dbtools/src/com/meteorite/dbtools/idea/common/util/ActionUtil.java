package com.meteorite.dbtools.idea.common.util;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ActionUtil {
    public static ActionToolbar createActionToolbar(String place, boolean horizontal, String actionGroupName) {
        ActionManager actionManager = ActionManager.getInstance();
        ActionGroup actionGroup = (ActionGroup) actionManager.getAction(actionGroupName);
        return actionManager.createActionToolbar(place, actionGroup, horizontal);
    }

    public static Project getProject(AnActionEvent e) {
        return e.getData(PlatformDataKeys.PROJECT);
    }
}
