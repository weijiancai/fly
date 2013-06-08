package com.meteorite.dbtools.idea.browser.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.meteorite.dbtools.idea.common.Icons;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class OpenSettingsAction extends AnAction {
    public OpenSettingsAction() {
        super("Settings", null, Icons.ACTION_SETTINGS);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
    }
}
