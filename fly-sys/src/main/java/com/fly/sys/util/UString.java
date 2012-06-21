package com.fly.sys.util;

import com.fly.common.util.StringUtil;

/**
 * @author weijiancai
 */
public class UString {
    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 将数据库的表名，准换为类名，例如sys_dbms_define,转换后的结果是DbmsDefine
     *
     * @param tableName 数据库表名
     * @return 返回类名
     */
    public static String tableNameToClassName(String tableName) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (String str : tableName.split("_")) {
            if (i++ == 0) {
                continue;
            }

            result.append(StringUtil.firstCharToUpper(str.toLowerCase()));
        }

        return result.toString();
    }

    public static String columnNameToFieldName(String columnName) {
        StringBuilder result = new StringBuilder();
        for (String str : columnName.split("_")) {
            result.append(StringUtil.firstCharToUpper(str.toLowerCase()));
        }

        return result.toString();
    }
}
