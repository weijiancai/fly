package com.fly.sys.db.object.impl;

import com.fly.sys.db.RowMapper;
import com.fly.sys.db.object.DBSchema;
import com.fly.sys.db.object.DBTable;
import com.fly.sys.db.object.impl.DBSchemaImpl;
import com.fly.sys.db.object.impl.DBTableImpl;

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

    public static RowMapper<DBTable> getDBTable() {
        return new RowMapper<DBTable>() {
            @Override
            public DBTable mapRow(ResultSet rs) throws SQLException {
                DBTableImpl table = new DBTableImpl();
                table.setName(rs.getString("TABLE_NAME"));
                table.setComment(rs.getString("TABLE_COMMENT"));
                return table;
            }
        };
    }
}
