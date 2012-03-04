package com.fly.base.db.util;

/**
 * 字符串实用工具类
 *
 * @author weijiancai
 * @version 1.0
 */
public class StringUtil {
    /**
     * 判断字符串是否为空（NULL或者“”）
     *
     * @param str 要判断的字符串
     * @return 字符串是否为空的标志，true为空，false非空
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * 判断字符串是否为非空（非NULL或者“”）
     *
     * @param str 要判断的字符串
     * @return 字符串是否为空的标志，true为非空，false空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 返回首字母大写的字符串
     */
    public static String firstCharToUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 如果字符串时null，则转化为空字符串，否则不进行转化
     *
     * @param str 需要转化的字符串
     * @return 如果str是null，返回"", 否则返回原字符串
     */
    public static String empty(String str) {
        return null == str ? "" : str;
    }
}
