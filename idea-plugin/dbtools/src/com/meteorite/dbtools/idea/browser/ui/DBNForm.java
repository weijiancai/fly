package com.meteorite.dbtools.idea.browser.ui;

import com.intellij.openapi.Disposable;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DBNForm extends Disposable {
    JComponent getComponent();

    boolean isDisposed();
}
