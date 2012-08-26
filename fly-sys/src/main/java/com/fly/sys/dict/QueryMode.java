package com.fly.sys.dict;

/**
 * 查询模式
 *
 * @author weijiancai
 * @version 1.0.0
 */
public enum QueryMode {
    /**
     * 0. =
     */
    EQUAL,
    /**
     * 1. <>
     */
    NOT_EQUAL,
    /**
     * 2. <
     */
    LESS_THAN,
    /**
     * 3. <=
     */
    LESS_EQUAL,
    /**
     * 4. >
     */
    GREATER_THAN,
    /**
     * 5. >=
     */
    GREATER_EQUAL,
    /**
     * 6. BETWEEN ... AND ...
     */
    BETWEEN,
    /**
     * 7. LIKE '%*%'
     */
    LIKE,
    /**
     * 8. LIKE '*%'
     */
    LEFT_LIKE,
    /**
     * 9. LIKE '%*'
     */
    RIGHT_LIKE
    ;

    public static QueryMode get(int queryMode) {
        if (queryMode == 0) {
            return QueryMode.EQUAL;
        } else if (queryMode == 1) {
            return QueryMode.NOT_EQUAL;
        } else if (queryMode == 2) {
            return QueryMode.LESS_THAN;
        } else if (queryMode == 3) {
            return QueryMode.LESS_EQUAL;
        } else if (queryMode == 4) {
            return QueryMode.GREATER_THAN;
        } else if (queryMode == 5) {
            return QueryMode.GREATER_EQUAL;
        } else if (queryMode == 6) {
            return QueryMode.BETWEEN;
        } else if (queryMode == 7) {
            return QueryMode.LIKE;
        } else if (queryMode == 8) {
            return QueryMode.LEFT_LIKE;
        } else if (queryMode == 9) {
            return QueryMode.RIGHT_LIKE;
        }

        return QueryMode.EQUAL;
    }
}
