package com.meteorite.cmd.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.meteorite.cmd.ui.CommandDialog;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class CommandAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        CommandDialog dialog = new CommandDialog();
        dialog.setVisible(true);
    }
}
