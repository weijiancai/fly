package com.meteorite.dbtools.idea.common.content;

import com.intellij.openapi.Disposable;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DynamicContentElement extends Disposable, Comparable {
    boolean isDisposed();
    String getName();
    String getDescription();
    void reload();
}
