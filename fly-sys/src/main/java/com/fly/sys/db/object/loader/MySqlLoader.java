package com.fly.sys.db.object.loader;

import com.fly.sys.db.object.DBConnection;
import com.fly.sys.db.object.DBLoader;
import com.fly.sys.db.object.DBSchema;
import com.fly.sys.db.object.DBTable;

import java.util.List;
import java.util.Map;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class MySqlLoader implements DBLoader {
    public static final String SCHEMAS = "select\n" +
            "                SCHEMA_NAME,\n" +
            "                'N' as IS_PUBLIC,\n" +
            "                if(lower(SCHEMA_NAME)='information_schema', 'Y', 'N') as IS_SYSTEM\n" +
            "            from INFORMATION_SCHEMA.SCHEMATA\n" +
            "            order by SCHEMA_NAME asc";

    private DBConnection conn;

    public MySqlLoader(DBConnection conn) {
        this.conn = conn;
    }

    @Override
    public List<DBSchema> loadSchemas() throws Exception {
        List<Map<String, Object>> list = conn.getResultSet(SCHEMAS);

        return null;
    }

    @Override
    public List<DBTable> loadTables() {
        return null;
    }
}
