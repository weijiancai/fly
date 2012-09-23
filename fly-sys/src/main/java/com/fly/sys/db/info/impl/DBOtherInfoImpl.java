package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBOtherInfo;
import com.fly.sys.db.info.TransactionIsolation;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 数据库其他信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
public class DBOtherInfoImpl implements DBOtherInfo {
    private DatabaseMetaData meta;

    public DBOtherInfoImpl() {}

    public DBOtherInfoImpl(DatabaseMetaData meta) {
        this.meta = meta;
    }

    public String getSchemaTerm() throws SQLException {
        return meta.getSchemaTerm();
    }

    public String getProcedureTerm() throws SQLException {
        return meta.getProcedureTerm();
    }

    public String getCatalogTerm() throws SQLException {
        return meta.getCatalogTerm();
    }

    public boolean isCatalogAtStart() throws SQLException {
        return meta.isCatalogAtStart();
    }

    public String getCatalogSeparator() throws SQLException {
        return meta.getCatalogSeparator();
    }

    public int getMaxBinaryLiteralLength() throws SQLException {
        return meta.getMaxBinaryLiteralLength();
    }

    public int getMaxCharLiteralLength() throws SQLException {
        return meta.getMaxCharLiteralLength();
    }

    public int getMaxColumnNameLength() throws SQLException {
        return meta.getMaxColumnNameLength();
    }

    public int getMaxColumnsInGroupBy() throws SQLException {
        return meta.getMaxColumnsInGroupBy();
    }

    public int getMaxColumnsInIndex() throws SQLException {
        return meta.getMaxColumnsInIndex();
    }

    public int getMaxColumnsInOrderBy() throws SQLException {
        return meta.getMaxColumnsInOrderBy();
    }

    public int getMaxColumnsInSelect() throws SQLException {
        return meta.getMaxColumnsInSelect();
    }

    public int getMaxColumnsInTable() throws SQLException {
        return meta.getMaxColumnsInTable();
    }

    public int getMaxConnections() throws SQLException {
        return meta.getMaxConnections();
    }

    public int getMaxCursorNameLength() throws SQLException {
        return meta.getMaxCursorNameLength();
    }

    public int getMaxIndexLength() throws SQLException {
        return meta.getMaxIndexLength();
    }

    public int getMaxSchemaNameLength() throws SQLException {
        return meta.getMaxSchemaNameLength();
    }

    public int getMaxProcedureNameLength() throws SQLException {
        return meta.getMaxProcedureNameLength();
    }

    public int getMaxCatalogNameLength() throws SQLException {
        return meta.getMaxCatalogNameLength();
    }

    public int getMaxRowSize() throws SQLException {
        return meta.getMaxRowSize();
    }

    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        return meta.doesMaxRowSizeIncludeBlobs();
    }

    public int getMaxStatementLength() throws SQLException {
        return meta.getMaxStatementLength();
    }

    public int getMaxStatements() throws SQLException {
        return meta.getMaxStatements();
    }

    public int getMaxTableNameLength() throws SQLException {
        return meta.getMaxTableNameLength();
    }

    public int getMaxTablesInSelect() throws SQLException {
        return meta.getMaxTablesInSelect();
    }

    public int getMaxUserNameLength() throws SQLException {
        return meta.getMaxUserNameLength();
    }

    public TransactionIsolation getDefaultTransactionIsolation() throws SQLException {
        switch (meta.getDefaultTransactionIsolation()) {
            case 0:
                return TransactionIsolation.TRANSACTION_NONE;
            case 1:
                return TransactionIsolation.TRANSACTION_READ_UNCOMMITTED;
            case 2:
                return TransactionIsolation.TRANSACTION_READ_COMMITTED;
            case 4:
                return TransactionIsolation.TRANSACTION_REPEATABLE_READ;
            case 8:
                return TransactionIsolation.TRANSACTION_SERIALIZABLE;
        }

        return null;
    }

    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        return meta.dataDefinitionCausesTransactionCommit();
    }

    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        return meta.dataDefinitionIgnoredInTransactions();
    }
}
