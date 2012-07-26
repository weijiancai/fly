package com.fly.sys.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class工具类
 *
 * @author weijiancai
 * @version 1.0
 */
public class ClassUtil {
    private static Log log = LogFactory.getLog(ClassUtil.class);

    /**
     * 获取类类型的所有Field包括父类中的Field
     *
     * @param clazz 类类型
     * @return 返回类类型的所有Field包括父类中的Field
     */
    public static Field[] getAllFields(Class clazz) {
        if (!clazz.getName().startsWith("com.fly")) {
            return new Field[0];
        }
        Map<String, Field> map = new HashMap<String, Field>();
        for (Field field : clazz.getDeclaredFields()) {
            map.put(field.getName(), field);
        }
        while (clazz.getSuperclass() != null) {
            clazz = clazz.getSuperclass();
            if (clazz == Object.class) {
                break;
            }

            for (Field field : clazz.getDeclaredFields()) {
                if (!map.containsKey(field.getName())) {
                    map.put(field.getName(), field);
                }
            }
        }
        return map.values().toArray(new Field[map.size()]);
    }

    public static <T> T getNullObject(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = getAllFields(clazz);
            for (Field field : fields) {
                if (field.getType() == String.class) {
                    try {
                        Method method = clazz.getMethod("set" + StringUtil.firstCharToUpper(field.getName()), field.getType());
                        method.invoke(t, "");
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        return t;
    }
}
