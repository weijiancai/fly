package com.meteorite.dbtools.idea.common.util;

import org.jetbrains.annotations.Nullable;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class CommonUtil {
    public static <T> T nvln(@Nullable T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
