package com.fly.sys.util;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class DomUtil {

    public static List<Map<String, String>> toList(String fileContent, String xpathStr) throws Exception {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        List list = XPath.newInstance(xpathStr).selectNodes(getDocument(fileContent));
        parse(result, list);

        return result;
    }

    public static List<Map<String, String>> toList(File file, String xpathStr) throws Exception {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        List list = XPath.newInstance(xpathStr).selectNodes(getDocument(file));
        parse(result, list);

        return result;
    }

    private static void parse(List<Map<String, String>> result, List list) {
        Map<String, String> map;
        Element element;
        Attribute attr;

        for (Object obj : list) {
            map = new HashMap<String, String>();
            result.add(map);

            if (obj instanceof Element) {
                element = (Element) obj;
                // 读取属性值
                for (Object aobj : element.getAttributes()) {
                    if (aobj instanceof Attribute) {
                        attr = (Attribute) aobj;
                        map.put(attr.getName(), attr.getValue());
                    }
                }
                // 读取子节点值
                for (Object cobj : element.getChildren()) {
                    if (cobj instanceof Element) {
                        element = (Element) cobj;
                        map.put(element.getName(), element.getValue());
                    }
                }
            }
        }
    }

    private static Document getDocument(File file) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        return builder.build(file);
    }

    private static Document getDocument(String fileContent) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        return builder.build(new StringReader(fileContent));
    }
}
