package com.meteorite.dbtools.idea.data.model;

import com.intellij.openapi.Disposable;
import com.meteorite.dbtools.idea.data.editor.ui.UserValueHolder;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DataModelCell extends Disposable, UserValueHolder {
    ColumnInfo getColumnInfo();

    int getIndex();

    DataModelRow getRow();

    boolean isDisposed();
}
