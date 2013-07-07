package com.fly.sys.db.metadata.impl;

import com.fly.sys.db.metadata.DBMetaDataLoader;
import com.fly.sys.db.object.DBSchema;
import com.fly.sys.db.object.DBTable;

import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class OracleMetadataLoader implements DBMetaDataLoader {
    @Override
    public List<DBSchema> loadSchemas() {
        return null;
    }

    @Override
    public List<DBTable> loadTables(String ownerName) {
        return null;
    }
}
