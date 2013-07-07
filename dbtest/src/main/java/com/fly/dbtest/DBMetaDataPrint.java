package com.fly.dbtest;

import java.sql.*;
import java.util.Map;

/**
 * 输入数据库元数据信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class DBMetaDataPrint {
    private Connection conn;

    public DBMetaDataPrint(Connection conn) {
        this.conn = conn;
    }

    public void print() {
        try {
            System.out.println("================================================================");
            System.out.println("-----------------------数据库连接信息----------------------------");

            print("Catalog", conn.getCatalog());
            print("AutoCommit", conn.getAutoCommit());
            print("isClosed", conn.isClosed());
            print("isReadOnly", conn.isReadOnly());
            try { // JDBC Version 1.7
                print("Schema", conn.getSchema());
                print("NetworkTimeout", conn.getNetworkTimeout());
            } catch (Error e) {
                // not handle
            }
            print("Holdability", conn.getHoldability());
            print("TransactionIsolation", conn.getTransactionIsolation());
            System.out.println("-------------ClientInfo---------------------");
            try {
                print(conn.getClientInfo());
            } catch (AbstractMethodError e) {
                System.out.println(e.getMessage());
            }
            System.out.println("--------------Type Map------------------------");
            print(conn.getTypeMap());
            System.out.println("--------------SQL Warning------------------------");
            SQLWarning warning = conn.getWarnings();
            if (warning != null) {
                while (warning.getNextWarning() != null) {
                    System.out.println(warning.getMessage());
                    warning = warning.getNextWarning();
                }
            }

            printLine();

            System.out.println("-----------------------数据库元数据信息----------------------------");

            DatabaseMetaData meta = conn.getMetaData();
            print("DatabaseProductName", meta.getDatabaseProductName());
            print("DatabaseProductVersion", meta.getDatabaseProductVersion());
            /*System.out.println(String.format("DatabaseMajorVersion: %s", meta.getDatabaseMajorVersion()));
            System.out.println(String.format("DatabaseMinorVersion: %s", meta.getDatabaseMinorVersion()));*/
            printLine();

            print("DriverName", meta.getDriverName());
            print("DriverVersion", meta.getDriverVersion());
            /*System.out.println(String.format("DriverMajorVersion: %s", meta.getDriverMajorVersion()));
            System.out.println(String.format("DriverMinorVersion: %s", meta.getDriverMinorVersion()));*/
            printLine();

            print("JDBCMajorVersion", meta.getJDBCMajorVersion());
            print("JDBCMinorVersion", meta.getJDBCMinorVersion());
            printLine();

            print("URL", meta.getURL());
            print("UserName", meta.getUserName());
            printLine();

            print("allProceduresAreCallable", meta.allProceduresAreCallable());
            print("allTablesAreSelectable", meta.allTablesAreSelectable());
            try {
                print("autoCommitFailureClosesAllResultSets", meta.autoCommitFailureClosesAllResultSets());
            } catch (AbstractMethodError e) {
                System.out.println(e.getMessage());
            }
            print("dataDefinitionCausesTransactionCommit", meta.dataDefinitionCausesTransactionCommit());
            print("dataDefinitionIgnoredInTransactions", meta.dataDefinitionIgnoredInTransactions());
            print("doesMaxRowSizeIncludeBlobs", meta.doesMaxRowSizeIncludeBlobs());

            printLine();
            print("CatalogSeparator", meta.getCatalogSeparator());
            print("CatalogTerm", meta.getCatalogTerm());
            System.out.println("Catalog: ");
            print(meta.getCatalogs());
            printLine();

            print("DefaultTransactionIsolation", meta.getDefaultTransactionIsolation());
            print("ExtraNameCharacters", meta.getExtraNameCharacters());
            print("IdentifierQuoteString", meta.getIdentifierQuoteString());
            printLine();

            print("MaxBinaryLiteralLength", meta.getMaxBinaryLiteralLength());
            print("MaxCatalogNameLength", meta.getMaxCatalogNameLength());
            print("MaxCharLiteralLength", meta.getMaxCharLiteralLength());
            print("MaxColumnNameLength", meta.getMaxColumnNameLength());
            print("MaxColumnsInGroupBy", meta.getMaxColumnsInGroupBy());
            print("MaxColumnsInIndex", meta.getMaxColumnsInIndex());
            print("MaxColumnsInOrderBy", meta.getMaxColumnsInOrderBy());
            print("MaxColumnsInSelect", meta.getMaxColumnsInSelect());
            print("MaxColumnsInTable", meta.getMaxColumnsInTable());
            print("MaxConnections", meta.getMaxConnections());
            print("MaxCursorNameLength", meta.getMaxCursorNameLength());
            print("MaxIndexLength", meta.getMaxIndexLength());
            print("MaxProcedureNameLength", meta.getMaxProcedureNameLength());
            print("MaxRowSize", meta.getMaxRowSize());
            print("MaxSchemaNameLength", meta.getMaxSchemaNameLength());
            print("MaxStatementLength", meta.getMaxStatementLength());
            print("MaxStatements", meta.getMaxStatements());
            print("MaxTableNameLength", meta.getMaxTableNameLength());
            print("MaxTablesInSelect", meta.getMaxTablesInSelect());
            print("MaxUserNameLength", meta.getMaxUserNameLength());
            printLine();

            print("ProcedureTerm", meta.getProcedureTerm());
            print("ResultSetHoldability", meta.getResultSetHoldability());
            print("SearchStringEscape", meta.getSearchStringEscape());
            print("SQLStateType", meta.getSQLStateType());
            print("isCatalogAtStart", meta.isCatalogAtStart());
            print("isReadOnly", meta.isReadOnly());
            printLine();

            print("locatorsUpdateCopy", meta.locatorsUpdateCopy());
            print("nullPlusNonNullIsNull", meta.nullPlusNonNullIsNull());
            print("nullsAreSortedAtEnd", meta.nullsAreSortedAtEnd());
            print("nullsAreSortedAtStart", meta.nullsAreSortedAtStart());
            print("nullsAreSortedHigh", meta.nullsAreSortedHigh());
            print("nullsAreSortedLow", meta.nullsAreSortedLow());
            printLine();

            print("storesLowerCaseIdentifiers", meta.storesLowerCaseIdentifiers());
            print("storesLowerCaseQuotedIdentifiers", meta.storesLowerCaseQuotedIdentifiers());
            print("storesMixedCaseIdentifiers", meta.storesMixedCaseIdentifiers());
            print("storesMixedCaseQuotedIdentifiers", meta.storesMixedCaseQuotedIdentifiers());
            print("storesUpperCaseIdentifiers", meta.storesUpperCaseIdentifiers());
            print("storesUpperCaseQuotedIdentifiers", meta.storesUpperCaseQuotedIdentifiers());
            printLine();

            print("supportsAlterTableWithAddColumn", meta.supportsAlterTableWithAddColumn());
            print("supportsAlterTableWithDropColumn", meta.supportsAlterTableWithDropColumn());
            print("supportsANSI92EntryLevelSQL", meta.supportsANSI92EntryLevelSQL());
            print("supportsANSI92FullSQL", meta.supportsANSI92FullSQL());
            print("supportsANSI92IntermediateSQL", meta.supportsANSI92IntermediateSQL());
            print("supportsBatchUpdates", meta.supportsBatchUpdates());
            print("supportsCatalogsInDataManipulation", meta.supportsCatalogsInDataManipulation());
            print("supportsCatalogsInIndexDefinitions", meta.supportsCatalogsInIndexDefinitions());
            print("supportsCatalogsInPrivilegeDefinitions", meta.supportsCatalogsInPrivilegeDefinitions());
            print("supportsCatalogsInProcedureCalls", meta.supportsCatalogsInProcedureCalls());
            print("supportsCatalogsInTableDefinitions", meta.supportsCatalogsInTableDefinitions());
            print("supportsColumnAliasing", meta.supportsColumnAliasing());
            print("supportsConvert", meta.supportsConvert());
            print("supportsCoreSQLGrammar", meta.supportsCoreSQLGrammar());
            print("supportsCorrelatedSubqueries", meta.supportsCorrelatedSubqueries());
            print("supportsDataDefinitionAndDataManipulationTransactions", meta.supportsDataDefinitionAndDataManipulationTransactions());
            print("supportsDataManipulationTransactionsOnly", meta.supportsDataManipulationTransactionsOnly());
            print("supportsDifferentTableCorrelationNames", meta.supportsDifferentTableCorrelationNames());
            print("supportsExpressionsInOrderBy", meta.supportsExpressionsInOrderBy());
            print("supportsExtendedSQLGrammar", meta.supportsExtendedSQLGrammar());
            print("supportsFullOuterJoins", meta.supportsFullOuterJoins());
            print("supportsGetGeneratedKeys", meta.supportsGetGeneratedKeys());
            print("supportsGroupBy", meta.supportsGroupBy());
            print("supportsGroupByBeyondSelect", meta.supportsGroupByBeyondSelect());
            print("supportsGroupByUnrelated", meta.supportsGroupByUnrelated());
            print("supportsIntegrityEnhancementFacility", meta.supportsIntegrityEnhancementFacility());
            print("supportsLikeEscapeClause", meta.supportsLikeEscapeClause());
            print("supportsLimitedOuterJoins", meta.supportsLimitedOuterJoins());
            print("supportsMinimumSQLGrammar", meta.supportsMinimumSQLGrammar());
            print("supportsMixedCaseIdentifiers", meta.supportsMixedCaseIdentifiers());
            print("supportsMixedCaseQuotedIdentifiers", meta.supportsMixedCaseQuotedIdentifiers());
            print("supportsMultipleOpenResults", meta.supportsMultipleOpenResults());
            print("supportsMultipleResultSets", meta.supportsMultipleResultSets());
            print("supportsMultipleTransactions", meta.supportsMultipleTransactions());
            print("supportsNamedParameters", meta.supportsNamedParameters());
            print("supportsNonNullableColumns", meta.supportsNonNullableColumns());
            print("supportsOpenCursorsAcrossCommit", meta.supportsOpenCursorsAcrossCommit());
            print("supportsOpenCursorsAcrossRollback", meta.supportsOpenCursorsAcrossRollback());
            print("supportsOpenStatementsAcrossCommit", meta.supportsOpenStatementsAcrossCommit());
            print("supportsOpenStatementsAcrossRollback", meta.supportsOpenStatementsAcrossRollback());
            print("supportsOrderByUnrelated", meta.supportsOrderByUnrelated());
            print("supportsOuterJoins", meta.supportsOuterJoins());
            print("supportsPositionedDelete", meta.supportsPositionedDelete());
            print("supportsPositionedUpdate", meta.supportsPositionedUpdate());

            print("supportsSavepoints", meta.supportsSavepoints());
            print("supportsSchemasInDataManipulation", meta.supportsSchemasInDataManipulation());
            print("supportsSchemasInIndexDefinitions", meta.supportsSchemasInIndexDefinitions());
            print("supportsSchemasInPrivilegeDefinitions", meta.supportsSchemasInPrivilegeDefinitions());
            print("supportsSchemasInProcedureCalls", meta.supportsSchemasInProcedureCalls());
            print("supportsSchemasInTableDefinitions", meta.supportsSchemasInTableDefinitions());
            print("supportsSelectForUpdate", meta.supportsSelectForUpdate());
            print("supportsStatementPooling", meta.supportsStatementPooling());
            print("supportsStoredProcedures", meta.supportsStoredProcedures());
            try {
                print("supportsStoredFunctionsUsingCallSyntax", meta.supportsStoredFunctionsUsingCallSyntax());
            } catch (AbstractMethodError e) {
                System.err.print(e.getMessage());
            }
            print("supportsSubqueriesInComparisons", meta.supportsSubqueriesInComparisons());
            print("supportsSubqueriesInExists", meta.supportsSubqueriesInExists());
            print("supportsSubqueriesInIns", meta.supportsSubqueriesInIns());
            print("supportsSubqueriesInQuantifieds", meta.supportsSubqueriesInQuantifieds());

            print("supportsTableCorrelationNames", meta.supportsTableCorrelationNames());
            print("supportsTransactions", meta.supportsTransactions());
            print("supportsUnion", meta.supportsUnion());
            print("supportsUnionAll", meta.supportsUnionAll());

            print("usesLocalFilePerTable", meta.usesLocalFilePerTable());
            print("usesLocalFiles", meta.usesLocalFiles());
            printLine();

            print("SchemaTerm", meta.getSchemaTerm());
            print("Schema:");
            print(meta.getSchemas());
            printLine();

            print("SQLKeywords", meta.getSQLKeywords());
            print("NumericFunctions", meta.getNumericFunctions());
            print("StringFunctions", meta.getStringFunctions());
            print("SystemFunctions", meta.getSystemFunctions());
            print("TimeDateFunctions", meta.getTimeDateFunctions());
            printLine();

            print("TableTypes: ");
            print(meta.getTableTypes());
            print("Tables: ");
            print(meta.getTables(null, null, null, null));
            print("Columns: ");
//            print(meta.getColumns(null, null, null, null));
            print("TypeInfo: ");
//            print(meta.getTypeInfo());
            printLine();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("================================================================");
    }

    private void printLine() {
        System.out.println();
    }
    
    private void print(String str) {
        System.out.println(str);
    }
    
    private void print(Object key, Object value) {
        System.out.println(String.format("%-30s = %s", key, value == null ? "" : value.toString()));
    }

    private void print(Map map) {
        if (map != null) {
            for (Object key : map.keySet()) {
                print(key, map.get(key));
            }
        }
    }

    private void print(ResultSet rs) {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();

            // 打印表头
            System.out.print("    ");
            for (int i = 1; i <= count; i++) {
                System.out.print(String.format("%-20s", meta.getColumnName(i)));
            }
            System.out.print("\n");
            print("    ---------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("    ");
                for (int i = 1; i <= count; i++) {
                    System.out.print(String.format("%-20s", rs.getString(i)));
                }
                System.out.print("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
