package com.meteorite.dbtools.idea.browser.ui;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class DBNFormImpl implements DBNForm {
    boolean disposed;

    @Override
    public boolean isDisposed() {
        return disposed;
    }

    @Override
    public void dispose() {
        disposed = true;
    }
}
