package com.fly.dbtest;

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
}
