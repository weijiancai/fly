package com.fly.sys.db.metadata;

import com.fly.sys.db.metadata.impl.MySqlMetadataLoader;
import com.fly.sys.db.metadata.impl.OracleMetadataLoader;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DBMetadataLoaderFactory {
    private static final OracleMetadataLoader ORACLE_METADATA_LOADER = new OracleMetadataLoader();

    public static DBMetadataLoader getLoader(DBConnectionParam connParam) {
        DatabaseType databaseType = connParam.getDatabaseType();

        if (databaseType == DatabaseType.ORACLE) {
            return ORACLE_METADATA_LOADER;
        } else if (databaseType == DatabaseType.MYSQL) {
            return MySqlMetadataLoader.getInstance(connParam);
        }

        return null;
    }
}
