package com.fly.sys.db.metadata.impl;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface MySqlMetadataStatement {
    String SCHEMAS = 
            "select " +
            "                SCHEMA_NAME, " +
            "                'N' as IS_PUBLIC, " +
            "                if(lower(SCHEMA_NAME)='information_schema', 'Y', 'N') as IS_SYSTEM " +
            "            from INFORMATION_SCHEMA.SCHEMATA " +
            "            order by SCHEMA_NAME asc";
    
    
    String TABLES = 
            "select " +
            "                TABLE_NAME, TABLE_COMMENT," +
            "                'N' as IS_TEMPORARY " +
            "            from  INFORMATION_SCHEMA.TABLES " +
            "            where " +
            "                TABLE_SCHEMA = ? and " +
            "                TABLE_TYPE = 'BASE TABLE' " +
            "            order by TABLE_NAME asc";
}
