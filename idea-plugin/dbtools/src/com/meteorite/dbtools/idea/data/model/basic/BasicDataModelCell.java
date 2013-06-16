package com.meteorite.dbtools.idea.data.model.basic;

import com.meteorite.dbtools.idea.data.model.ColumnInfo;
import com.meteorite.dbtools.idea.data.model.DataModelCell;
import com.meteorite.dbtools.idea.data.model.DataModelRow;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class BasicDataModelCell implements DataModelCell {
    @Override
    public ColumnInfo getColumnInfo() {
        return null;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public DataModelRow getRow() {
        return null;
    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public void dispose() {
    }
}
