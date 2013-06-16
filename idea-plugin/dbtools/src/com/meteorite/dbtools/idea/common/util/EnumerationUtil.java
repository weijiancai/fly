package com.meteorite.dbtools.idea.common.util;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class EnumerationUtil {
    public static <T extends Enum<T>> boolean isOneOf(Enum<T> enumeration, Enum<T> ... values) {
        for (Enum value : values) {
            if (value == enumeration) return true;
        }
        return false;
    }
}
