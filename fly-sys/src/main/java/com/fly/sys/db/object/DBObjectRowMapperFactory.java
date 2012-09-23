package com.fly.sys.db.object;

import com.fly.sys.db.RowMapper;
import com.fly.sys.db.object.impl.DBSchemaImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DBObjectRowMapperFactory {
    public static RowMapper<DBSchema> getDBSchema() {
        return new RowMapper<DBSchema>() {
            @Override
            public DBSchema mapRow(ResultSet rs) throws SQLException {
                DBSchemaImpl schema = new DBSchemaImpl();
                schema.setName(rs.getString("SCHEMA_NAME"));
                return schema;
            }
        };
    }
}
