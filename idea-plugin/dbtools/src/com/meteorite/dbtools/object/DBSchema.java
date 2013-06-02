package com.meteorite.dbtools.object;

import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DBSchema extends DBObject {
    List<DBTable> getTables();

    List<DBView> getViews();

    List<DBIndex> getIndexes();

    List<DBSynonym> getSynonyms();

    List<DBSequence> getSequences();

    List<DBProcedure> getProcedures();

    List<DBFunction> getFunctions();

    List<DBPackage> getPackages();

    List<DBTrigger> getTriggers();
}
