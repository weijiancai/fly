package com.meteorite.dbtools.idea.common.util;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;

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
}
