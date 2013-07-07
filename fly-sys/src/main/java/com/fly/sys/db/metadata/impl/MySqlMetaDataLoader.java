package com.fly.sys.db.metadata.impl;

import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.db.metadata.DBConnectionParam;
import com.fly.sys.db.metadata.DBMetaDataLoader;
import com.fly.sys.db.object.impl.DBObjectRowMapperFactory;
import com.fly.sys.db.object.DBSchema;
import com.fly.sys.db.object.DBTable;
import com.fly.sys.db.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class MySqlMetaDataLoader implements DBMetaDataLoader {
    private static MySqlMetaDataLoader mySqlMetadataLoader;
    private DBConnectionParam connParam;
    private Connection conn;

    private MySqlMetaDataLoader(DBConnectionParam connParam) {
        this.connParam = connParam;
    }

    @Override
    public List<DBSchema> loadSchemas() {
        List<DBSchema> schemas = new ArrayList<DBSchema>();

        Connection conn = null;
        try {
            conn = getConn();
            JdbcTemplate template = new JdbcTemplate(conn);

            schemas = template.query(MySqlMetadataStatement.SCHEMAS, DBObjectRowMapperFactory.getDBSchema());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection(conn);
        }

        return schemas;
    }

    @Override
    public List<DBTable> loadTables(String ownerName) {
        List<DBTable> schemas = new ArrayList<DBTable>();

        Connection conn = null;
        try {
            conn = getConn();
            JdbcTemplate template = new JdbcTemplate(conn);

            schemas = template.query(MySqlMetadataStatement.TABLES, DBObjectRowMapperFactory.getDBTable(), ownerName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection(conn);
        }

        return schemas;
    }

    private Connection getConn() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            conn = ConnectionUtil.getConnection(connParam);
        }

        return conn;
    }

    public static MySqlMetaDataLoader getInstance(DBConnectionParam connParam) {
        if (mySqlMetadataLoader == null) {
            mySqlMetadataLoader = new MySqlMetaDataLoader(connParam);
        }

        return mySqlMetadataLoader;
    }
}
