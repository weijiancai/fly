package com.fly.sys.persist.db.meta;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public enum Nullable {
    NO_NULLS,
    NULLABLE,
    NULLABLE_UNKNOWN
    ;

    public static Nullable getNullable(int type) {
        switch (type) {
            case 0:
                return NO_NULLS;
            case 1:
                return NULLABLE;
            case 2:
                return NULLABLE_UNKNOWN;
        }

        return null;
    }
}
