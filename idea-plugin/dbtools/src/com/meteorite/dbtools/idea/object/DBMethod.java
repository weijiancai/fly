package com.meteorite.dbtools.idea.object;

import com.meteorite.dbtools.idea.object.common.DBSchemaObject;
import com.meteorite.dbtools.idea.object.identifier.DBMethodIdentifier;

import java.util.List;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DBMethod extends DBSchemaObject {
    DBMethodIdentifier getIdentifier();
    List<DBArgument> getArguments();

    DBArgument getArgument(String name);

    DBProgram getProgram();

    String getMethodType();

    int getOverload();

    boolean isEmbedded();

    boolean isDeterministic();

    boolean hasDeclaredArguments();
}
