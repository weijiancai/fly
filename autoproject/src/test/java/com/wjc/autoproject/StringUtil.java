package com.wjc.autoproject;

/**
 *
 * @author weijiancai
 * @version 1.0
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }


    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    public static String firstCharToUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String empty(String str) {
        return null == str ? "" : str;
    }
}
