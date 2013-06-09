package com.meteorite.dbtools.idea.object;

import java.util.List;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DBProcedure extends DBMethod {
    List<DBArgument> getArguments();

    DBArgument getArgument(name);
}
