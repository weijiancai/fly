package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBSupportsInfo;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
public class DBSupportsInfoImpl implements DBSupportsInfo {
    private DatabaseMetaData meta;

    public DBSupportsInfoImpl() {}

    public DBSupportsInfoImpl(DatabaseMetaData meta) {
        this.meta = meta;
    }

    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        return meta.supportsMixedCaseIdentifiers();
    }

    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        return meta.supportsMixedCaseQuotedIdentifiers();
    }

    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        return meta.supportsAlterTableWithAddColumn();
    }

    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        return meta.supportsAlterTableWithDropColumn();
    }

    public boolean supportsColumnAliasing() throws SQLException {
        return meta.supportsColumnAliasing();
    }

    public boolean supportsConvert() throws SQLException {
        return meta.supportsConvert();
    }

    public boolean supportsTableCorrelationNames() throws SQLException {
        return meta.supportsTableCorrelationNames();
    }

    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        return meta.supportsDifferentTableCorrelationNames();
    }

    public boolean supportsExpressionsInOrderBy() throws SQLException {
        return meta.supportsExpressionsInOrderBy();
    }

    public boolean supportsOrderByUnrelated() throws SQLException {
        return meta.supportsOrderByUnrelated();
    }

    public boolean supportsGroupBy() throws SQLException {
        return meta.supportsGroupBy();
    }

    public boolean supportsGroupByUnrelated() throws SQLException {
        return meta.supportsGroupByUnrelated();
    }

    public boolean supportsGroupByBeyondSelect() throws SQLException {
        return meta.supportsGroupByBeyondSelect();
    }

    public boolean supportsLikeEscapeClause() throws SQLException {
        return meta.supportsLikeEscapeClause();
    }

    public boolean supportsMultipleResultSets() throws SQLException {
        return meta.supportsMultipleResultSets();
    }

    public boolean supportsMultipleTransactions() throws SQLException {
        return meta.supportsMultipleTransactions();
    }

    public boolean supportsNonNullableColumns() throws SQLException {
        return meta.supportsNonNullableColumns();
    }

    public boolean supportsMinimumSQLGrammar() throws SQLException {
        return meta.supportsMinimumSQLGrammar();
    }

    public boolean supportsCoreSQLGrammar() throws SQLException {
        return meta.supportsCoreSQLGrammar();
    }

    public boolean supportsExtendedSQLGrammar() throws SQLException {
        return meta.supportsExtendedSQLGrammar();
    }

    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        return meta.supportsANSI92EntryLevelSQL();
    }

    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        return meta.supportsANSI92IntermediateSQL();
    }

    public boolean supportsANSI92FullSQL() throws SQLException {
        return meta.supportsANSI92FullSQL();
    }

    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        return meta.supportsIntegrityEnhancementFacility();
    }

    public boolean supportsOuterJoins() throws SQLException {
        return meta.supportsOuterJoins();
    }

    public boolean supportsFullOuterJoins() throws SQLException {
        return meta.supportsFullOuterJoins();
    }

    public boolean supportsLimitedOuterJoins() throws SQLException {
        return meta.supportsLimitedOuterJoins();
    }

    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return meta.supportsSchemasInDataManipulation();
    }

    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        return meta.supportsSchemasInProcedureCalls();
    }

    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return meta.supportsSchemasInTableDefinitions();
    }

    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return meta.supportsSchemasInIndexDefinitions();
    }

    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        return meta.supportsSchemasInPrivilegeDefinitions();
    }

    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        return meta.supportsCatalogsInDataManipulation();
    }

    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        return meta.supportsCatalogsInProcedureCalls();
    }

    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        return meta.supportsCatalogsInTableDefinitions();
    }

    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        return meta.supportsCatalogsInIndexDefinitions();
    }

    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        return meta.supportsCatalogsInPrivilegeDefinitions();
    }

    public boolean supportsPositionedDelete() throws SQLException {
        return meta.supportsPositionedDelete();
    }

    public boolean supportsPositionedUpdate() throws SQLException {
        return meta.supportsPositionedUpdate();
    }

    public boolean supportsSelectForUpdate() throws SQLException {
        return meta.supportsSelectForUpdate();
    }

    public boolean supportsStoredProcedures() throws SQLException {
        return meta.supportsStoredProcedures();
    }

    public boolean supportsSubqueriesInComparisons() throws SQLException {
        return meta.supportsSubqueriesInComparisons();
    }

    public boolean supportsSubqueriesInExists() throws SQLException {
        return meta.supportsSubqueriesInExists();
    }

    public boolean supportsSubqueriesInIns() throws SQLException {
        return meta.supportsSubqueriesInIns();
    }

    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        return meta.supportsSubqueriesInQuantifieds();
    }

    public boolean supportsCorrelatedSubqueries() throws SQLException {
        return meta.supportsCorrelatedSubqueries();
    }

    public boolean supportsUnion() throws SQLException {
        return meta.supportsUnion();
    }

    public boolean supportsUnionAll() throws SQLException {
        return meta.supportsUnionAll();
    }

    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        return meta.supportsOpenCursorsAcrossCommit();
    }

    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        return meta.supportsOpenCursorsAcrossRollback();
    }

    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        return meta.supportsOpenStatementsAcrossCommit();
    }

    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        return meta.supportsOpenStatementsAcrossRollback();
    }

    public boolean supportsTransactions() throws SQLException {
        return meta.supportsTransactions();
    }

    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        return meta.supportsDataDefinitionAndDataManipulationTransactions();
    }

    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        return meta.supportsDataManipulationTransactionsOnly();
    }
}
