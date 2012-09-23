package com.fly.sys.db.object;

import java.util.List;

/**
 * 数据库Schema
 *
 * @author weijiancai
 * @version 1.0.0
 */
public interface DBSchema extends DBObject {
    List<DBTable> getTables();
    List<DBView> getViews();
    List<DBProcedure> getProcedures();
    List<DBFunction> getFunctions();

    DBTable getTable(String name);
    DBView getView(String name);
    DBProcedure getProcedure(String name);
    DBFunction getFunction(String name);
}
