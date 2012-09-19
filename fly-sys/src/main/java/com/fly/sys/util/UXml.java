package com.fly.sys.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Xml 工具类
 *
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

    public static <T> T toObject(String xmlStr, Class<T> clazz) throws Exception {
        if (UString.isEmpty(xmlStr)) {
            return null;
        }
        Document doc = getDocument(xmlStr);

        return toObject(doc.getDocumentElement(), clazz);
    }

    private static <T> T toObject(Element element, Class<T> clazz) throws Exception {
        if (element == null) {
            return null;
        }

        T obj = null;

        if (clazz == int.class || clazz == Integer.class || "Integer".equals(element.getTagName())) {
            obj = getIntegerObject(element, clazz);
        } else if (clazz == long.class || clazz == Long.class || "Long".equals(element.getTagName())) {
            obj = getLongObject(element, clazz);
        } else if (clazz == float.class || clazz == Float.class || "Float".equals(element.getTagName())) {
            obj = getFloatObject(element, clazz);
        } else if (clazz == double.class || clazz == Double.class || "Double".equals(element.getTagName())) {
            obj = getDoubleObject(element, clazz);
        } else if (clazz == char.class || clazz == Character.class || "Character".equals(element.getTagName())) {
            obj = getCharacterObject(element, clazz);
        } else if (clazz == boolean.class || clazz == Boolean.class || "Boolean".equals(element.getTagName())) {
            obj = getBooleanObject(element, clazz);
        } else if (clazz == String.class) {
            obj = getStringObject(element, clazz);
        } else if (clazz == Date.class) {
            obj = getDateObject(element, clazz);
        } else {
            obj = getBeanObject(element, clazz);
        }

        return obj;
    }

    private static <T> T getLongObject(Element element, Class<T> clazz) throws Exception {
        T obj;
        long value = 0;
        try {
            value = Long.parseLong(element.getTextContent());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // no handle
        }
        obj = clazz.getConstructor(long.class).newInstance(value);
        return obj;
    }

    private static <T> T getIntegerObject(Element element, Class<T> clazz) throws Exception {
        T obj;
        int value = 0;
        try {
            value = Integer.parseInt(element.getTextContent());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // no handle
        }
        obj = clazz.getConstructor(int.class).newInstance(value);
        return obj;
    }

    private static <T> T getFloatObject(Element element, Class<T> clazz) throws Exception {
        T obj;
        float value = 0;
        try {
            value = Float.parseFloat(element.getTextContent());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // no handle
        }
        obj = clazz.getConstructor(float.class).newInstance(value);
        return obj;
    }

    private static <T> T getDoubleObject(Element element, Class<T> clazz) throws Exception {
        T obj;
        double value = 0;
        try {
            value = Double.parseDouble(element.getTextContent());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // no handle
        }
        obj = clazz.getConstructor(double.class).newInstance(value);
        return obj;
    }

    private static <T> T getCharacterObject(Element element, Class<T> clazz) throws Exception {
        T obj;
        Character value = null;
        try {
            if (UString.isNotEmpty(element.getTextContent())) {
                value = element.getTextContent().charAt(0);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // no handle
        }
        obj = clazz.getConstructor(char.class).newInstance(value);
        return obj;
    }

    private static <T> T getBooleanObject(Element element, Class<T> clazz) throws Exception {
        T obj;
        Boolean value = false;
        try {
            if (UString.isNotEmpty(element.getTextContent())) {
                value = Boolean.valueOf(element.getTextContent());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // no handle
        }
        obj = clazz.getConstructor(boolean.class).newInstance(value);
        return obj;
    }

    private static <T> T getStringObject(Element element, Class<T> clazz) throws Exception {
        return clazz.getConstructor(String.class).newInstance(element.getTextContent());
    }

    private static <T> T getDateObject(Element element, Class<T> clazz) throws Exception {
        long date;
        try {
            date = Long.parseLong(element.getTextContent());
        } catch (NumberFormatException e) {
            return null;
        }
        return clazz.getConstructor(long.class).newInstance(date);
    }

    private static <T> T getBeanObject(Element element, Class<T> clazz) throws Exception {
        T obj = null;

        String methodName, fieldName;
        for (Method method : clazz.getDeclaredMethods()) {
            methodName = method.getName();
            if (methodName.startsWith("set")) {
                fieldName = UString.firstCharToLower(methodName.substring(3));
                Class<?> paramType = getFirstMethodParamType(method);

            }
        }
        return obj;
    }

    private static Class<?> getFirstMethodParamType(Method method) {
        Class<?>[] types = method.getParameterTypes();
        if (types != null && types.length > 0) {
            return types[0];
        }

        return null;
    }

    public static Document getDocument(String xmlStr) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new InputSource(new StringReader(xmlStr)));
    }
}
