package com.fly.sys.db;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public enum DbmsType {
    MYSQL("mysql"), ORACLE("oracle"), SQL_SERVER("sql server"), UNKNOWN("未知");

    private String name;

    private DbmsType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
