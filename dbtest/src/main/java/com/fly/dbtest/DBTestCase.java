package com.fly.dbtest;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * 数据库测试用例
 *
 * @author weijiancai
 * @version 1.0.0
 */
public abstract class DBTestCase implements ConnectionParam {
    private static Map<String, String> CONNECTION_PARAM = new HashMap<String, String>();

    private static DBMetaDataPrint metaPrint;
    private static Connection conn;
    private static QueryRunner qr;

    @BeforeClass
    public static void setup() {
        try {
            initConnectionParam();
            DbUtils.loadDriver(getParam(JDBC_DRIVER));
            conn = DriverManager.getConnection(getParam(JDBC_URL), getParam(JDBC_USER_NAME), getParam(JDBC_PASSWORD));
            metaPrint = new DBMetaDataPrint(conn);
            qr = new QueryRunner();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        qr = null;
        DbUtils.close(conn);
    }

    public void hasCatalog(String catalogName) {
        try {
            RSHandler handler = new RSHandler(conn.getMetaData().getCatalogs());
            if (!handler.contains(1, catalogName)) {
                fail(String.format("找不到Catalog【%s】", catalogName));
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void hasTable(String tableName) {
        hasTable("找不到表【%s】", tableName, null);
    }

    public void hasTable(String tableName, TableType type) {
        hasTable("找不到表【%s】", tableName, type);
    }

    public void hasView(String viewName) {
        hasTable("找不到视图【%s】", viewName, TableType.VIEW);
    }

    private void hasTable(String message, String tableName, TableType type) {
        ResultSet rs;
        try {
            if (type == null) {
                rs = conn.getMetaData().getTables(null, null, tableName.toUpperCase(), null);
            } else {
                rs = conn.getMetaData().getTables(null, null, tableName.toUpperCase(), new String[]{type.toString()});
            }
            if (!rs.next()) {
                fail(String.format(message, tableName));
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void hasTableCount(String tableName, int count) {
        hasTable(tableName);
        try {
            int i = qr.query(conn, String.format("SELECT count(1) FROM %s", tableName), ResultSetHandlerFactory.singleInt());
            assertThat(String.format("%s表count总数：", tableName), i, equalTo(count));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void hasColumn(String tableName, String columnName) {
        hasTable(tableName);
        try {
            ResultSet rs = conn.getMetaData().getColumns(null, null, tableName.toUpperCase(), columnName.toUpperCase());
            if (!rs.next()) {
                fail(String.format("表【%s】中不存在列【%s】", tableName, columnName));
            }
            rs.close();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public Object getColumnInfo(String tableName, String columnName, ColumnInfo info) {
        hasTable(tableName);
        Object result = null;
        try {
            ResultSet rs = conn.getMetaData().getColumns(null, null, tableName.toUpperCase(), columnName.toUpperCase());
            if (rs.next()) {
                result = rs.getObject(info.toString());
            } else {
                fail(String.format("表【%s】中不存在列【%s】", tableName, columnName));
            }
            rs.close();
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        return result;
    }

    public void hasRecord(String tableName, Object obj) {
        try {
            String sql = String.format("SELECT * FROM %s", tableName);
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            RSHandler handler = new RSHandler(rs);
            if (!handler.contains(obj)) {
                fail(String.format("不存在此条记录【%s】", obj.toString()));
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    private static String getParam(String key) {
        return CONNECTION_PARAM.get(key);
    }

    /**
     * 初始化数据库的连接参数Map
     */
    private static void initConnectionParam() throws Exception {
        InputStream is = DBTestCase.class.getResourceAsStream("/dbconfig.properties");
        if (is == null) {
            throw new Exception("请在类路径中配置dbconfig.properties,内容为\n" +
                    "url=\n" +
                    "driverClass=\n" +
                    "userName=\n" +
                    "password=\n"
            );
        }
        try {
            Properties p = new Properties();
            p.load(is);
            for (String key : p.stringPropertyNames()) {
                CONNECTION_PARAM.put(key, p.getProperty(key));
            }
            is.close();
        } catch (IOException e) {
            throw new Exception("加载配置文件dbconfig.properties失败！！！", e);
        }
    }

    protected void printMetaData() {
        metaPrint.print();
    }
}
