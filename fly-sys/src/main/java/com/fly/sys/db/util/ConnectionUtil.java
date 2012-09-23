package com.fly.sys.db.util;

import com.fly.sys.db.metadata.DBConnectionParam;
import com.fly.sys.db.metadata.DatabaseType;

import java.sql.*;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ConnectionUtil {
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Statement statement = resultSet.getStatement();
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(String driverName, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        Connection conn;

        Class.forName(driverName);
        if (userName == null && password == null) {
            conn = DriverManager.getConnection(url);
        } else {
            conn = DriverManager.getConnection(url, userName, password);
        }

        return conn;
    }

    public static Connection getConnection(DBConnectionParam connParam) throws ClassNotFoundException, SQLException {
        return getConnection(connParam.getDriverName(), connParam.getUrl(), connParam.getUserName(), connParam.getPassword());
    }

    public static DatabaseType getDatabaseType(Connection connection) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String productName = databaseMetaData.getDatabaseProductName();
        if (productName.toUpperCase().contains("ORACLE")) {
            return DatabaseType.ORACLE;
        } else if (productName.toUpperCase().contains("MYSQL")) {
            return DatabaseType.MYSQL;
        }
        return DatabaseType.UNKNOWN;
    }
}
