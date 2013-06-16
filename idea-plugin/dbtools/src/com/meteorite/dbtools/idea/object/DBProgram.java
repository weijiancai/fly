package com.meteorite.dbtools.idea.object;

import com.meteorite.dbtools.idea.object.common.DBSchemaObject;
import com.meteorite.dbtools.object.DBFunction;

import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DBProgram<P extends DBProcedure, F extends DBFunction> extends DBSchemaObject {
    List<P> getProcedures();
    List<F> getFunctions();
    F getFunction(String name, int overload);
    P getProcedure(String name, int overload);
    DBMethod getMethod(String name, int overload);
    boolean isEmbedded();
}
