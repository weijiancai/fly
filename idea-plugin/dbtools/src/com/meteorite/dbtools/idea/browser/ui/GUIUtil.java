package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.ui.Splitter;

import javax.swing.*;
import java.awt.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class GUIUtil {
    public static void updateSplitterProportion(final JComponent root, final float proportion) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (root instanceof Splitter) {
                    Splitter splitter = (Splitter) root;
                    splitter.setProportion(proportion);
                } else {
                    Component[] components = root.getComponents();
                    for (Component component : components) {
                        if (component instanceof JComponent) {
                            updateSplitterProportion((JComponent) component, proportion);
                        }
                    }
                }
            }
        });
    }
}
