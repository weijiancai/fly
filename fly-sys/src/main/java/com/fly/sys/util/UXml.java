package com.fly.sys.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Xml 工具类
 * @author weijiancai
 * @version 1.0.0
 */
public class UXml {
    public static String toXmlString(List<Object> list) throws Exception {
        return toXmlString(list, true);
    }

    public static String toXmlString(List<Object> list, boolean containRoot) throws Exception {
        if (list == null || list.size() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        if (containRoot) {
            result.append("<List>");
        }

        for (Object object : list) {
            result.append(toXmlString(object, true));
        }

        if (containRoot) {
            result.append("</List>");
        }

        return result.toString();
    }

    public static String toXmlString(Map<String, Object> map) throws Exception {
        return toXmlString(map, true);
    }

    public static String toXmlString(Map<String, Object> map, boolean containRoot) throws Exception {
        if (map == null || map.size() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        if (containRoot) {
            result.append("<Map>");
        }

        Object value;
        for (String key : map.keySet()) {
            value = map.get(key);
            if (value != null) {
                addAttr(result, key, value);
            }
        }

        if (containRoot) {
            result.append("</Map>");
        }

        return result.toString();
    }

    public static String toXmlString(Object object) throws Exception {
        return toXmlString(object, true);
    }

    public static String toXmlString(Object object, boolean containRoot) throws Exception {
        if (object == null) {
            return "";
        }

        Class clazz = object.getClass();
        if (isPrimitiveType(clazz)) {
            return "<" + clazz.getSimpleName() + ">" + object.toString() + "</" + clazz.getSimpleName() + ">";
        }

        if (clazz.getName().startsWith("java")) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        StringBuilder attr = new StringBuilder();

        if (containRoot) {
            result.append("<").append(clazz.getSimpleName()).append(">");
        }

        String getMethodName;
        Object value = null;
        for (Field field : clazz.getDeclaredFields()) {
            getMethodName = getFieldGetMethodName(field.getName());
            Method method = null;
            try {
                method = clazz.getMethod(getMethodName);
            } catch (Exception e) {
                // no handle
            }
            if (method != null) {
                value = method.invoke(object);
            } else {
                if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    try {
                        method = clazz.getMethod(getFieldBooleanMethodName(field.getName()));
                    } catch (Exception e) {
                        // no handle
                    }
                    if (method != null) {
                        value = method.invoke(object);
                    }
                }
            }

            if (value != null) {
                addAttr(attr, field.getName(), value);
            }
        }
        result.append(attr);

        if (containRoot) {
            result.append("</").append(clazz.getSimpleName()).append(">");
        }

        return result.toString();
    }

    @SuppressWarnings("unchecked")
    private static void addAttr(StringBuilder sb, String attrName, Object value) throws Exception {
        if (isPrimitiveType(value.getClass())) {
            addAttr(sb, attrName, value.toString());
        } else if (value.getClass().isAssignableFrom(Date.class)) {
            addAttr(sb, attrName, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
        } else if (value.getClass().isAssignableFrom(Map.class)) {
            addAttr(sb, attrName, toXmlString((Map<String, Object>)value, false));
        } else {
            addAttr(sb, attrName, toXmlString(value, false));
        }
    }

    private static void addAttr(StringBuilder sb, String attrName, String value) {
        sb.append("<").append(attrName).append(">").append(value).append("</").append(attrName).append(">");
    }

    private static String getFieldGetMethodName(String fieldName) {
        return "get" + UString.firstCharToUpper(fieldName);
    }

    private static String getFieldBooleanMethodName(String fieldName) {
        return "is" + (fieldName.startsWith("is") ? fieldName.substring(2) : UString.firstCharToUpper(fieldName));
    }

    private static boolean isPrimitiveType(Class clazz) {
        return clazz.isAssignableFrom(int.class) || clazz.isAssignableFrom(Integer.class) ||
                clazz.isAssignableFrom(long.class) || clazz.isAssignableFrom(Long.class) ||
                clazz.isAssignableFrom(float.class) || clazz.isAssignableFrom(Float.class) ||
                clazz.isAssignableFrom(double.class) || clazz.isAssignableFrom(Double.class) ||
                clazz.isAssignableFrom(char.class) || clazz.isAssignableFrom(Character.class) ||
                clazz.isAssignableFrom(boolean.class) || clazz.isAssignableFrom(Boolean.class) ||
                clazz.isAssignableFrom(String.class);
    }
}
