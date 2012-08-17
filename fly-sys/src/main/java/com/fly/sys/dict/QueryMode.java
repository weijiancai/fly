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
    LEFT_EQUAL,
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
}
