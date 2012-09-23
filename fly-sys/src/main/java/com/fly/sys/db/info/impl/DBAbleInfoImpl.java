package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBAbleInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
public class DBAbleInfoImpl implements DBAbleInfo {
    private DatabaseMetaData meta;

    public DBAbleInfoImpl() {}

    public DBAbleInfoImpl(DatabaseMetaData meta) {
        this.meta = meta;
    }

    @XmlElement
    public boolean getAllProceduresAreCallable() throws SQLException {
        return meta.allProceduresAreCallable();
    }

    @XmlElement
    public boolean getAllTablesAreSelectable() throws SQLException {
        return meta.allTablesAreSelectable();
    }

    @XmlElement
    public boolean getUsesLocalFiles() throws SQLException {
        return meta.usesLocalFiles();
    }

    @XmlElement
    public boolean getUsesLocalFilePerTable() throws SQLException {
        return meta.usesLocalFilePerTable();
    }

    @XmlElement
    public boolean getStoresUpperCaseIdentifiers() throws SQLException {
        return meta.storesUpperCaseIdentifiers();
    }

    @XmlElement
    public boolean getStoresLowerCaseIdentifiers() throws SQLException {
        return meta.storesLowerCaseIdentifiers();
    }

    @XmlElement
    public boolean getStoresMixedCaseIdentifiers() throws SQLException {
        return meta.storesMixedCaseIdentifiers();
    }

    @XmlElement
    public boolean getStoresUpperCaseQuotedIdentifiers() throws SQLException {
        return meta.storesUpperCaseQuotedIdentifiers();
    }

    @XmlElement
    public boolean getStoresLowerCaseQuotedIdentifiers() throws SQLException {
        return meta.storesLowerCaseQuotedIdentifiers();
    }

    @XmlElement
    public boolean getStoresMixedCaseQuotedIdentifiers() throws SQLException {
        return meta.storesMixedCaseQuotedIdentifiers();
    }
}
