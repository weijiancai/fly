package com.fly.sys.db.metadata;

public enum DatabaseType {
    ORACLE("ORACLE", "Oracle"),
    MYSQL("MYSQL", "MySQL"),
    UNKNOWN("UNKNOWN", "Unknown Database");

    private String name;
    private String displayName;

    DatabaseType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DatabaseType get(String name) {
        for (DatabaseType databaseType : values()) {
            if (name.equalsIgnoreCase(databaseType.getName())) return databaseType;
        }
        return null;
    }
}
