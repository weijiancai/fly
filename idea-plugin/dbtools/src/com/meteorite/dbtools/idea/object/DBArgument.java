package com.meteorite.dbtools.idea.object;

import com.meteorite.dbtools.idea.data.type.DBDataType;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DBArgument extends DBObject {
    DBDataType getDataType();
    DBMethod getMethod();
    int getOverload();
    int getPosition();
    int getSequence();
    boolean isInput();
    boolean isOutput();
}
