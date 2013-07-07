package com.fly.sys.db.object;

import java.util.List;

/**
 * 数据库Schema，这里将Schema和Catalog进行了合并，不使用DBCatalog对象，因为不是所有的数据库都支持Catalog
 * <p/>
 * ===============================================================================
 * 数据库厂商           Catalog                              Schema
 * Oracle             不支持                                Oracle User ID
 * MySQL              不支持                                数据库名
 * MS Sql Server      数据库名                              对象属主名，2005版开始有变化
 * DB2                指定对象数据库时，Catalog部分省略        Catalog属主名
 * Sybase             数据库名                              数据库属主名
 * Informix           不支持                                不需要
 * PointBase          不支持                                数据库名
 * ===============================================================================
 *
 * @author weijiancai
 * @version 1.0.0
 */
public interface DBSchema extends DBObject {
    String getCatalog();

    List<DBTable> getTables();

    void setTables(List<DBTable> tables);

    List<DBView> getViews();

    List<DBProcedure> getProcedures();

    List<DBFunction> getFunctions();

    List<DBIndex> getIndexes();

    List<DBSynonym> getSynonyms();

    List<DBSequence> getSequences();

    List<DBPackage> getPackages();

    List<DBTrigger> getTriggers();

    DBTable getTable(String name);

    DBView getView(String name);

    DBProcedure getProcedure(String name);

    DBFunction getFunction(String name);
}
