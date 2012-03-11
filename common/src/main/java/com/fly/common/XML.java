package com.fly.common;

import com.fly.common.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析XML文档，将XML结构，转换为对象
 *
 * @author weijiancai
 */
public class XML {
    private Document doc;
    
    public XML(File file) {
        try {
            // 创建DOM对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);

            // 初始化
            doc.normalize();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T toObject(String tagName, Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> list = toList(tagName, clazz);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }

    public <T> Map<String, T> toMap(String tagName, String keyName, Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        final Map<String, T> map = new HashMap<String, T>();
        
        iteratorNode(tagName, clazz, keyName, new Callback<T>() {
            @Override
            public void call(T t, Object... obj) {
                String key = obj.length > 0 ? obj[0].toString() : null;
                if (StringUtil.isNotEmpty(key)) {
                    map.put(key, t);
                }
            }
        });
        
        return map;
    }
    
    public <T> List<T> toList(String tagName, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        final List<T> list = new ArrayList<T>();

        iteratorNode(tagName, clazz, null, new Callback<T>() {
            @Override
            public void call(T t, Object... obj) {
                list.add(t);
            }
        });
        
        return list;
    }

    private <T> void iteratorNode(String tagName, Class<T> clazz, String keyName, Callback<T> callback) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // 得到节点集合
        NodeList dataSources = doc.getElementsByTagName(tagName);
        // 循环遍历节点
        Node node;
        T obj;
        String fieldName;
        String nodeValue;
        String key = null;
        for (int i = 0; i < dataSources.getLength(); i++) {
            node = dataSources.item(i);
            obj = clazz.newInstance();

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().startsWith("set")) {
                    fieldName = firstCharToLower(method.getName().substring(3));
                    // 读取节点属性值，如果没有此属性值，则读子节点值
                    nodeValue = getAttrValue(node, fieldName);
                    if (StringUtil.isEmpty(nodeValue)) {
                        nodeValue = getNodeValue(node, fieldName);
                    }
                    if (fieldName.equalsIgnoreCase(keyName)) {
                        key = nodeValue;
                    }
                    // 设置属性值
                    method.invoke(obj, nodeValue);
                }
            }

            callback.call(obj, key);
        }
    }

    private String firstCharToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private static String getNodeValue(Node node, String tagName) {
        return ((Element)node).getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue();
    }

    private static String getAttrValue(Node node, String attrName) {
        return ((Element) node).getAttribute(attrName);
    }
}
