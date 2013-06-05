package com.meteorite.dbtools.idea.common;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class Icons {
    public static final Icon WINDOW_DATABASE_BROWSER = load("/img/window/DatabaseBrowser.png");

    public static final Icon BROWSER_BACK = load("/img/action/BrowserBack.png");

    private static Icon load(String path) {
        return IconLoader.getIcon(path);
    }
}
