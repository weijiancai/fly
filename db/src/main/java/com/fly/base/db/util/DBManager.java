package com.fly.base.db.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class DBManager {
    private Connection conn;
    private static List<DataSource> dataSourceList = new ArrayList<DataSource>();
    private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

    static {
        try {
            XML xml = new XML(FileUtil.getFileFromClassPath("/datasource.xml"));
            dataSourceList = xml.toList("datasource", DataSource.class);
            dataSourceMap = xml.toMap("datasource", "name", DataSource.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public DBManager() {
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "7758521");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public Connection getConn() {
        return conn;
    }

    public Connection getConn(String dsname) {
        DataSource ds = dataSourceMap.get(dsname);
        try {
            Class.forName(ds.getDriverClass());
            return  DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public DataSource getDataSource(String dsname) {
        return dataSourceMap.get(dsname);
    }
}
