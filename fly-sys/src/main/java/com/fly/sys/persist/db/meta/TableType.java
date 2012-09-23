package com.fly.sys.persist.db.meta;

/**
 * 数据库表类型
 *
 * @author weijiancai
 * @version 1.0.0
 */
public enum TableType {
    TABLE("TABLE"),
    VIEW("VIEW"),
    SYSTEM_TABLE("SYSTEM TABLE"),
    GLOBAL_TEMPORARY("GLOBAL TEMPORARY"),
    LOCAL_TEMPORARY("LOCAL TEMPORARY"),
    ALIAS("ALIAS"),
    SYNONYM("SYNONYM")
    ;

    private String type;

    private TableType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static TableType getTableType(String type) {
        if ("TABLE".equals(type)) {
            return TableType.TABLE;
        } else if ("VIEW".equals(type)) {
            return TableType.VIEW;
        } else if ("SYSTEM TABLE".equals(type)) {
            return TableType.SYSTEM_TABLE;
        } else if ("GLOBAL TEMPORARY".equals(type)) {
            return TableType.GLOBAL_TEMPORARY;
        } else if ("LOCAL TEMPORARY".equals(type)) {
            return TableType.LOCAL_TEMPORARY;
        } else if ("ALIAS".equals(type)) {
            return TableType.ALIAS;
        } else if ("SYNONYM".equals(type)) {
            return TableType.SYNONYM;
        }

        return null;
    }
}
