package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.*;
import com.fly.sys.db.metadata.DBConnectionParam;
import com.fly.sys.db.metadata.DBMetadataLoader;
import com.fly.sys.db.metadata.DBMetadataLoaderFactory;
import com.fly.sys.db.object.DBSchema;
import com.fly.sys.db.util.ConnectionUtil;
import com.fly.sys.db.util.ExceptionWrapper;
import com.fly.sys.persist.db.meta.DbCatalog;
import com.fly.sys.persist.db.meta.MetaDataUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
@XmlType(propOrder = {"databaseProductName", "databaseProductVersion",
        "driverName", "driverVersion", "driverMajorVersion", "driverMinorVersion"
        , "URL", "userName", "password", "readOnly",
        "tableTypes", "schemas", "catalogs",
        "sqlKeywords", "numericFunctionsInfo", "stringFunctionsInfo", "systemFunctionsInfo", "timeDateFunctionsInfo",
 "identifierQuoteString", "searchStringEscape", "extraNameCharacters"
})
public class DBInfoImpl implements DBInfo {
    private String url;
    private String password;
    private String userName;
    private boolean isReadOnly;
    private String identifierQuoteString;
    private String searchStringEscape;
    private String extraNameCharacters;

    private String databaseProductName;
    private String databaseProductVersion;

    private String driverName;
    private String driverVersion;
    private int driverMajorVersion;
    private int driverMinorVersion;

    private String sqlKeywords;

    private DBNumericFunctionsInfoImpl numericFunctionsInfo;
    private DBStringFunctionsInfoImpl stringFunctionsInfo;
    private DBSystemFunctionsInfoImpl systemFunctionsInfo;
    private DBTimeDateFunctionsInfoImpl timeDateFunctionsInfo;
    private DBOtherInfo otherInfo;
    private List<DbCatalog> catalogs;
    private String tableTypes;

    private List<DBSchema> schemas;

    public DBInfoImpl() {}

    public DBInfoImpl(String driverName, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        this.password = password;
        Connection conn;

        Class.forName(driverName);
        if (userName == null && password == null) {
            conn = DriverManager.getConnection(url);
        } else {
            conn = DriverManager.getConnection(url, userName, password);
        }
        DatabaseMetaData meta = conn.getMetaData();

        this.url = meta.getURL();
        this.userName = meta.getUserName();
        this.isReadOnly = meta.isReadOnly();
        this.identifierQuoteString = meta.getIdentifierQuoteString();
        this.searchStringEscape = meta.getSearchStringEscape();
        this.extraNameCharacters = meta.getExtraNameCharacters();

        this.databaseProductName = meta.getDatabaseProductName();
        this.databaseProductVersion = meta.getDatabaseProductVersion();

        this.driverName = meta.getDriverName();
        this.driverVersion = meta.getDriverVersion();
        this.driverMajorVersion = meta.getDriverMajorVersion();
        this.driverMinorVersion = meta.getDriverMajorVersion();

        this.sqlKeywords = meta.getSQLKeywords();

        numericFunctionsInfo = new DBNumericFunctionsInfoImpl(meta.getNumericFunctions());
        stringFunctionsInfo = new DBStringFunctionsInfoImpl(meta.getStringFunctions());
        systemFunctionsInfo = new DBSystemFunctionsInfoImpl(meta.getSystemFunctions());
        timeDateFunctionsInfo = new DBTimeDateFunctionsInfoImpl(meta.getTimeDateFunctions());
        otherInfo = new DBOtherInfoImpl(meta);
        catalogs = MetaDataUtil.getCatalogs(meta);
        tableTypes = MetaDataUtil.getTableTypes(meta);

        DBMetadataLoader loader = DBMetadataLoaderFactory.getLoader(new DBConnectionParam(ConnectionUtil.getDatabaseType(conn), driverName, url, userName, password));
        schemas = loader.loadSchemas();

        conn.close();

    }

    public DBInfoImpl(String driverName, String url) throws ClassNotFoundException, SQLException {
        this(driverName, url, null, null);
    }

    @XmlElement(name = "SQLKeywords")
    public String getSqlKeywords() {
        return sqlKeywords;
    }

    @XmlElement(name = "NumericFunctions")
    public DBNumericFunctionsInfoImpl getNumericFunctionsInfo() {
        return numericFunctionsInfo;
    }

    @XmlElement(name = "StringFunctions")
    public DBStringFunctionsInfoImpl getStringFunctionsInfo() {
        return stringFunctionsInfo;
    }

    @XmlElement(name = "SystemFunctions")
    public DBSystemFunctionsInfoImpl getSystemFunctionsInfo() {
        return systemFunctionsInfo;
    }

    @XmlElement(name = "TimeDateFunctions")
    public DBTimeDateFunctionsInfoImpl getTimeDateFunctionsInfo() {
        return timeDateFunctionsInfo;
    }

    public DBOtherInfo getOtherInfo() {
        return otherInfo;
    }

    @XmlElement(name = "Catalog")
    public List<DbCatalog> getCatalogs() {
        return catalogs;
    }

    @XmlElement(name = "Schema")
    public List<DBSchema> getSchemas() {
        return schemas;
    }

    @XmlElement
    public String getURL() {
        return url;
    }

    @XmlElement
    public String getUserName() {
        return userName;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    @XmlElement
    public boolean isReadOnly() {
        return isReadOnly;
    }

    @XmlElement
    public String getIdentifierQuoteString() {
        return identifierQuoteString;
    }

    @XmlElement
    public String getSearchStringEscape() {
        return searchStringEscape;
    }

    @XmlElement
    public String getExtraNameCharacters() {
        return extraNameCharacters;
    }

    @XmlElement
    public String getDatabaseProductName() {
        return databaseProductName;
    }

    @XmlElement
    public String getDatabaseProductVersion() {
        return databaseProductVersion;
    }

    @XmlElement
    public String getDriverName() {
        return driverName;
    }

    @XmlElement
    public String getDriverVersion() {
        return driverVersion;
    }

    @XmlElement
    public int getDriverMajorVersion() {
        return driverMajorVersion;
    }

    @XmlElement
    public int getDriverMinorVersion() {
        return driverMinorVersion;
    }

    @XmlElement(name = "TableTypes")
    public String getTableTypes() {
        return tableTypes;
    }
}
