package com.meteorite.dbtools.idea.object;


/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DBTypeProcedure extends DBProcedure {
    DBType getType();
    int getOverload();
}
