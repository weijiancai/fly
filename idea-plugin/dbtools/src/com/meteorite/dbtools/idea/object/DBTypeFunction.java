package com.meteorite.dbtools.idea.object;

import com.meteorite.dbtools.object.DBFunction;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DBTypeFunction extends DBFunction {
    public DBType getType();
    int getOverload();
}
