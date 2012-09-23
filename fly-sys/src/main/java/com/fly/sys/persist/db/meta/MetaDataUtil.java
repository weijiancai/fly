package com.fly.sys.persist.db.meta;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class MetaDataUtil {
    public static List<DbCatalog> getCatalogs(DatabaseMetaData meta) throws SQLException {
        List<DbCatalog> catalogs = new ArrayList<DbCatalog>();
        ResultSet rs = meta.getCatalogs();

        DbCatalog catalog;
        while (rs.next()) {
            catalog = new DbCatalog();
            catalog.setName(rs.getString("TABLE_CAT"));
            catalogs.add(catalog);
            catalog.setProcedures(getProcedures(meta, catalog.getName()));

            /*Map<String, List<DbTable>> tableMap = new HashMap<String, List<DbTable>>();
            tableMap.put(TableType.TABLE.toString(), getTables(meta, catalog.getName(), TableType.TABLE));
            tableMap.put(TableType.VIEW.toString(), getTables(meta, catalog.getName(), TableType.VIEW));
            tableMap.put(TableType.SYSTEM_TABLE.toString(), getTables(meta, catalog.getName(), TableType.SYSTEM_TABLE));
            tableMap.put(TableType.GLOBAL_TEMPORARY.toString(), getTables(meta, catalog.getName(), TableType.GLOBAL_TEMPORARY));
            tableMap.put(TableType.LOCAL_TEMPORARY.toString(), getTables(meta, catalog.getName(), TableType.LOCAL_TEMPORARY));
            tableMap.put(TableType.ALIAS.toString(), getTables(meta, catalog.getName(), TableType.ALIAS));
            tableMap.put(TableType.SYNONYM.toString(), getTables(meta, catalog.getName(), TableType.SYNONYM));
            catalog.setTableMap(tableMap);*/
            catalog.setTables(getTables(meta, catalog.getName()));
        }

        return catalogs;
    }

    public static List<DbProcedure> getProcedures(DatabaseMetaData meta, String catalog) throws SQLException {
        List<DbProcedure> procedures = new ArrayList<DbProcedure>();
        ResultSet rs = meta.getProcedures(catalog, null, null);

        DbProcedure procedure;
        while (rs.next()) {
            procedure = new DbProcedure();
            procedure.setCatalog(rs.getString("PROCEDURE_CAT"));
            procedure.setSchema(rs.getString("PROCEDURE_SCHEM"));
            procedure.setName(rs.getString("PROCEDURE_NAME"));
            procedure.setRemarks(rs.getString("REMARKS"));
            switch (rs.getInt("PROCEDURE_TYPE")) {
                case 0:
                    procedure.setType(ProcedureType.PROCEDURE_RESULT_UNKNOWN);
                    break;
                case 1:
                    procedure.setType(ProcedureType.PROCEDURE_NO_RESULT);
                    break;
                case 2:
                    procedure.setType(ProcedureType.PROCEDURE_RETURN_RESULT);
                    break;
            }
            procedure.setSpecificName(rs.getString("SPECIFIC_NAME"));
            procedure.setProcedureColumns(getProcedureColumns(meta, catalog, procedure.getName()));

            procedures.add(procedure);
        }

        return procedures;
    }

    public static List<ProcedureColumn> getProcedureColumns(DatabaseMetaData meta, String catalog, String procedure) throws SQLException {
        List<ProcedureColumn> procedureColumns = new ArrayList<ProcedureColumn>();
        ResultSet rs = meta.getProcedureColumns(catalog, null, procedure, null);

        ProcedureColumn procedureColumn;
        while (rs.next()) {
            procedureColumn = new ProcedureColumn();
            procedureColumn.setColumnName(rs.getString("COLUMN_NAME"));
            procedureColumn.setColumnType(ProcedureColumnType.getColumnType(rs.getInt("COLUMN_TYPE")));
            procedureColumn.setDataType(rs.getInt("DATA_TYPE"));
            procedureColumn.setTypeName(rs.getString("TYPE_NAME"));
            procedureColumn.setPrecision(rs.getInt("PRECISION"));
            procedureColumn.setLength(rs.getInt("LENGTH"));
            procedureColumn.setScale(rs.getShort("SCALE"));
            procedureColumn.setRadix(rs.getShort("RADIX"));
            procedureColumn.setNullable(Nullable.getNullable(rs.getInt("NULLABLE")));
            procedureColumn.setRemarks(rs.getString("REMARKS"));
//            procedureColumn.setDefaultValue(rs.getString("COLUMN_DEF"));
//            procedureColumn.setSqlDataType(rs.getInt("SQL_DATA_TYPE"));
//            procedureColumn.setSqlDateTimeSub(rs.getInt("SQL_DATETIME_SUB"));
//            procedureColumn.setCharOctetLength(rs.getInt("CHAR_OCTET_LENGTH"));

            procedureColumns.add(procedureColumn);
        }

        return procedureColumns;
    }

    public static String getTableTypes(DatabaseMetaData meta) throws SQLException {
        StringBuilder result = new StringBuilder();
        ResultSet rs = meta.getTableTypes();

        while (rs.next()) {
            result.append(rs.getString("TABLE_TYPE")).append(",");
        }

        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    public static List<DbTable> getTables(DatabaseMetaData meta, String catalog, TableType... tableTypes) throws SQLException {
        List<DbTable> tables = new ArrayList<DbTable>();
        ResultSet rs;
        if (tableTypes == null) {
            rs = meta.getTables(catalog, null, null, null);
        } else {
            String[] types = new String[tableTypes.length];
            int i = 0;
            for (TableType type : tableTypes) {
                types[i++] = type.toString();
            }

            rs = meta.getTables(catalog, null, null, types);
        }

        DbTable table;
        while (rs.next()) {
            table = new DbTable();
            table.setName(rs.getString("TABLE_NAME"));
            table.setTableType(TableType.getTableType(rs.getString("TABLE_TYPE")));
            table.setRemarks(rs.getString("REMARKS"));
//            table.setTypeCatalog(rs.getString("TYPE_CAT"));
//            table.setTypeSchema(rs.getString("TYPE_SCHEM"));
//            table.setTypeName(rs.getString("TYPE_NAME"));
//            table.setSelfReferencingColName(rs.getString("SELF_REFERENCING_COL_NAME"));
//            table.setRefGeneration(rs.getString("REF_GENERATION"));

            tables.add(table);
        }

        return tables;
    }
}
