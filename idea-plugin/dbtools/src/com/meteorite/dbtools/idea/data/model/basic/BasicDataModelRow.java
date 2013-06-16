package com.meteorite.dbtools.idea.data.model.basic;

import com.meteorite.dbtools.idea.data.model.DataModelCell;
import com.meteorite.dbtools.idea.data.model.DataModelRow;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BasicDataModelRow<T extends DataModelCell> implements DataModelRow<T> {
    @Override
    public void dispose() {
    }
}
