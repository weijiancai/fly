package com.fly.dbtest;

/**
 * 列信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
public enum ColumnInfo {
    TABLE_CAT("TABLE_CAT"),
    TABLE_SCHEM("TABLE_SCHEM"),
    TABLE_NAME("TABLE_NAME"),
    COLUMN_NAME("COLUMN_NAME"),
    DATA_TYPE("DATA_TYPE"),
    TYPE_NAME("TYPE_NAME"),
    COLUMN_SIZE("COLUMN_SIZE"),
    BUFFER_LENGTH("BUFFER_LENGTH"),
    DECIMAL_DIGITS("DECIMAL_DIGITS"),
    NUM_PREC_RADIX("NUM_PREC_RADIX"),
    NULLABLE("NULLABLE"),
    REMARKS("REMARKS"),
    COLUMN_DEF("COLUMN_DEF"),
    SQL_DATA_TYPE("SQL_DATA_TYPE"),
    SQL_DATETIME_SUB("SQL_DATETIME_SUB"),
    CHAR_OCTET_LENGTH("CHAR_OCTET_LENGTH"),
    ORDINAL_POSITION("ORDINAL_POSITION"),
    IS_NULLABLE("IS_NULLABLE"),
    SCOPE_CATLOG("SCOPE_CATLOG"),
    SCOPE_SCHEMA("SCOPE_SCHEMA"),
    SCOPE_TABLE("SCOPE_TABLE"),
    SOURCE_DATA_TYPE("SOURCE_DATA_TYPE")
    ;

    private String info;

    private ColumnInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
}
