package com.meteorite.dbtools.idea.data.model;

import com.meteorite.dbtools.idea.data.type.DBDataType;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface ColumnInfo {
    String getName();

    int getColumnIndex();

    DBDataType getDataType();

    boolean isSortable();
}
