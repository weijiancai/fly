package com.fly.common.util;

/**
 * 数组工具类
 *
 * @author  weijiancai
 * @version $Reversion$
 */
public class ArrayUtil {
    /**
     * 将字符串中所有字符串转换为大写
     *
     * @param strings 将要转换的字符串数组
     */
    public static void toUpper(String[] strings) {
        if (isNotEmpty(strings)) {
            for (int i = 0; i < strings.length; i++) {
                if (StringUtil.isNotEmpty(strings[i])) {
                    strings[i] = strings[i].toUpperCase();
                }
            }
        }
    }

    public static <T> boolean isEmpty(T[] t) {
        return null == t || t.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] t) {
        return !isEmpty(t);
    }
}
