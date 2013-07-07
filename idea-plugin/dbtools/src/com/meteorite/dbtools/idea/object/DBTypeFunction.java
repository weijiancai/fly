package com.meteorite.dbtools.idea.object;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DBTypeFunction extends DBObject {
    public DBType getType();
    int getOverload();
}
