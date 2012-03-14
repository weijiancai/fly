package com.fly.common.json;

import com.fly.common.util.ArrayUtil;
import com.fly.common.util.ClassUtil;
import com.fly.common.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

/**
 * 将对象转换成JSON字符串形式
 *
 * @author weijiancai
 * @version 1.0
 */
public class Json {
    private static Log log = LogFactory.getLog(Json.class);
    public static enum TYPE {FORMAT, REPLACE}

    /**
     * 将key，value转换为json对象
     *
     * @param key   json的key
     * @param value json的value
     * @return json对象
     */
    public static String toJsonString(String key, String value) {
        if (StringUtil.isNotEmpty(key)) {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"").append(formatForJson(key)).append("\":\"").append(formatForJson(value)).append("\"}");

            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 将List类型转换为JSON格式的数组
     *
     * @param list     要转换的List
     * @param includes 要包含的属性
     * @return 返回JSON格式的数组
     */
    public static String toJsonList(List list,String... includes) {
        return _toJsonList(list, TYPE.FORMAT, includes);
    }

    private static String _toJsonList(List list, TYPE type, String... includes) {
        if (null == list || list.size() == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Object obj : list) {
            result.append(_toJsonString(obj, type, includes)).append(",");
        }
        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("]");

        return result.toString();
    }

    public static String toJsonString(Set set, String... includes) {
        if (null == set || set.size() == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Object obj : set) {
            if (obj instanceof String) {
                result.append("\"").append(obj.toString()).append("\",");
            } else if (obj instanceof Integer || obj instanceof Float || obj instanceof Double || obj instanceof BigDecimal || obj instanceof Boolean) {
                result.append(obj.toString()).append(",");
            } else {
                result.append(toJsonString(obj)).append(",");
            }
        }
        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("]");

        return result.toString();
    }

    public static String toJsonString(Map map) {
        if (null == map || map.size() == 0) {
            return "{}";
        }

        StringBuilder result = new StringBuilder();
        result.append("{");
        for (Object key : map.keySet()) {
            result.append("\"").append(key).append("\":");
            if (map.get(key) instanceof String) {
                result.append("\"").append(map.get(key)).append("\",");
            } else if (map.get(key) instanceof Integer || map.get(key) instanceof Float || map.get(key) instanceof Double || map.get(key) instanceof BigDecimal || map.get(key) instanceof Boolean) {
                result.append(map.get(key)).append(",");
            } else {
                result.append(toJsonString(map.get(key))).append(",");
            }
        }
        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("}");

        return result.toString();
    }

    /**
     * 将Object类型转换为JSON格式的字符串
     * 注意：现在只支持普通Object、List类型
     *
     * @param obj      要转换的对象
     * @param includes 要包含的属性
     * @return 返回JSON格式的字符串
     */
    public static String toJsonString(Object obj, String... includes) {
        return _toJsonString(obj, TYPE.FORMAT, includes);
    }

    public static String replaceToJson(Object obj, String... includes) {
        return _toJsonString(obj, TYPE.REPLACE, includes);
    }

    public static String replaceToJson(List list, String... includes) {
        return _toJsonList(list, TYPE.REPLACE, includes);
    }

    private static String _toJsonString(Object obj, TYPE type, String... includes) {
        if(null == obj) return "{}";
        if (obj instanceof List) {
            return toJsonList((List) obj, includes);
        }
        if (obj instanceof Map) {
            return toJsonString((Map) obj);
        }

        List<String> list = null;
        if (ArrayUtil.isNotEmpty(includes)) {
            ArrayUtil.toUpper(includes);
            list = Arrays.asList(includes);
        }

        StringBuffer result = new StringBuffer();
        Class clazz = obj.getClass();
        Field[] fields = ClassUtil.getAllFields(clazz);
        Method method;

        result.append("{");
        for (Field field : fields) {
            if (null != field.getAnnotation(NoJSON.class)) continue;
            String name = field.getName();
            if (null != list && !list.contains(name.toUpperCase())) continue;
            if(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) continue;

            try {
                method = clazz.getMethod("get" + firstCharToUpper(name));
                Object value = method.invoke(obj);
                if (field.getType() == String.class) {
                    String str = "";
                    if (null != value) {
                        switch (type) {
                            case FORMAT:
                                name = formatForJson(name);
                                str = formatForJson(value.toString());
                                break;
                            case REPLACE:
                                name = replaceForJson(name);
                                str = replaceForJson(value.toString());
                                break;
                            default:
                                str = value.toString();
                        }
                    }
                    result.append('"').append(name).append("\":\"").append(str).append("\",");
                } else if (field.getType() == boolean.class) {
                    result.append('"').append(name).append("\":").append(value).append(",");
                } else if (field.getType() == List.class) {
                    result.append('"').append(name).append("\":").append(toJsonList((List) value)).append(",");
                } else if (field.getType() == Map.class) {
                    result.append('"').append(name).append("\":").append(toJsonString((Map) value)).append(",");
                } else if (value instanceof Object) {
                    result.append('"').append(name).append("\":").append(toJsonString(value)).append(",");
                } else if (null == value) {
                    result.append('"').append(name).append("\":{},");
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }

        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("}");

        return result.toString();
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
     * 将字符串格式化为JSON格式的字符串
     *
     * @param str 需要格式化的字符串
     * @return 替换掉特殊符号后的字符串
     */
    public static String formatForJson(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '"') {
                sb.append("\\\"");
            } else if (c == '\\') {
                sb.append("\\\\");
            } else if (c == '/') {
                sb.append("\\/");
            } else if (c == '\b') {
                sb.append("\\b");
            } else if (c == '\f') {
                sb.append("\\f");
            } else if (c == '\n') {
                sb.append("\\n");
            } else if (c == '\r') {
                sb.append("\\r");
            } else if (c == '\t') {
                sb.append("\\t");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String replaceForJson(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        return str.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\r", "\\\\\\\\r").replaceAll("\\n", "\\\\\\\\n").replaceAll("'", "\\\\'").replaceAll("\"", "\\\\\\\\\"");
    }
    
    public static List<Map<String, Object>> toList(String jsonStr) {
        if (StringUtil.isEmpty(jsonStr)) {
            return new ArrayList<Map<String, Object>>();
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        if (jsonStr.startsWith("[")) {
            
        }

        // 一个堆栈保存符号[ {
        // 一个字符串保存key，一个字符串保存value
        // 遇到=号保存key，遇到,}保存value，遇到}]弹出{[
        // 取出类中所有字段信息，以类名.字段名作为key
        // 参数增加类名前缀，参数名save,update,delete,query，参数值采用json格式。
        // pstmt.setObject(obj)

        Stack<Character> stack = new Stack<Character>();
        StringBuilder key = null, value = null;
        List<Map<String, Object>> list = null;
        String str;
        boolean isValue = false;
        for(char c : jsonStr.toCharArray()) {
            if(c == '[') {
                stack.push(c);
                list = new ArrayList<Map<String, Object>>();
            } else if (c == '{') {
                stack.push(c);
                map = new HashMap<String, Object>();
                key = new StringBuilder();
            } else if (c == ':') {
                isValue = true;
                value = new StringBuilder();
            } else if (c == ',') {
                isValue = false;
                str = key.toString();
                if(str.startsWith("\"") || str.startsWith("'")) {
                    str = key.substring(1);
                }
                if (str.endsWith("\"") || str.endsWith("'")) {
                    str = str.substring(0, str.length() - 1);
                }
                if((value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') || (value.charAt(0) == '\'' && value.charAt(value.length() - 1) == '\'')) {
                    map.put(str, value.substring(1, value.length() - 1));
                } else  {
                    map.put(str, value.toString());
                }

                key = new StringBuilder();
            } else if (c == '}') {
                isValue = false;
                if(stack.peek() == '{') {
                    stack.pop();
                    str = key.toString();
                    if(str.startsWith("\"") || str.startsWith("'")) {
                        str = key.substring(1);
                    }
                    if (str.endsWith("\"") || str.endsWith("'")) {
                        str = str.substring(0, str.length() - 1);
                    }
                    if((value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') || (value.charAt(0) == '\'' && value.charAt(value.length() - 1) == '\'')) {
                        map.put(str, value.substring(1, value.length() - 1));
                    } else  {
                        map.put(str, value.toString());
                    }
                    list.add(map);
                } else {
                    throw new RuntimeException("JSON 语法不正确 没有正确匹配 }");
                }
            } else if (c == ']') {
                isValue = false;
                if(stack.peek() == '[') {
                    stack.pop();
                } else {
                    throw new RuntimeException("JSON 语法不正确 没有正确匹配 ]");
                }
            } else {
                if (!isValue) {
                    key.append(c);
                } else {
                    value.append(c);
                }
            }
        }

        return list;
    }
    
    public static Map<String, Object> toMap(String jsonStr) {
        if (StringUtil.isEmpty(jsonStr)) {
            return new HashMap<String, Object>();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        String str = jsonStr.substring(jsonStr.startsWith("{") ? 1 : 0, jsonStr.endsWith("}") ? jsonStr.length() - 1 : jsonStr.length());

        return map;
    }
}
