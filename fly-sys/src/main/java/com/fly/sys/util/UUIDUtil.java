package com.fly.sys.util;

import java.util.UUID;

/**
 * @author weijiancai
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
