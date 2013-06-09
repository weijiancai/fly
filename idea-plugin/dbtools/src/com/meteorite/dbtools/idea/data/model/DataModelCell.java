package com.meteorite.dbtools.idea.data.model;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DataModelCell {
    ColumnInfo getColumnInfo();

    int getIndex();

    DataModelRow getRow();

    boolean isDisposed();
}
