package com.wjc.autoproject;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wjc.autoproject.Constant.*;

/**
 * @author weijiancai
 */
public class AutoParser {
    private File xmlFile;
    private Element rootElement;
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private Coder coder;

    public AutoParser(File xmlFile) throws JDOMException, IOException {
        this.xmlFile = xmlFile;
        coder = new Coder();
        coder.setProjectName(xmlFile.getParentFile().getName());
        coder.setBaseDir(xmlFile.getParentFile());

        Document doc = new SAXBuilder().build(xmlFile);
        rootElement = doc.getRootElement();

        init();
        System.out.println(coder);
    }

    private void init() throws JDOMException {
        coder.setProjectName(getAttributeValue(AP_PROJECT_NAME));
        String baseDir = getAttributeValue(AP_BASE_DIR);
        if (null != baseDir) {
            coder.setBaseDir(new File(baseDir));
        }
        coder.setBasePackage(getAttributeValue(AP_BASE_PACKAGE));

        coder.setUseMaven(Boolean.valueOf(getAttributeValue(AP_USE_MAVEN)));
        if (coder.isUseMaven()) {
            parseMaven();
        }


    }

    private String getElementText(String xpath) throws JDOMException {
        Element element = (Element)XPath.selectSingleNode(rootElement, xpath);
        if (element != null) {
            return element.getTextTrim();
        }

        return null;
    }

    private String getAttributeValue(String xpath) throws JDOMException {
        Attribute attribute = (Attribute) XPath.selectSingleNode(rootElement, xpath);
        if (null != attribute) {
            return attribute.getValue();
        }

        return null;
    }

    private void parseMaven() throws JDOMException {
        coder.setPom_groupId(getElementText(POM_GROUP_ID));
        coder.setPom_artifactId(getElementText(POM_ARTIFACT_ID));
        coder.setPom_version(getElementText(POM_VERSION));
        coder.setPom_packaging(getElementText(POM_PACKAGING));
    }

    private void parsePo() throws JDOMException {
        String pos = getElementText(POS_NODE);
        if (StringUtil.isNotEmpty(pos)) {
            coder.setGenPo(true);

            List<FieldInfo> poList = new ArrayList<FieldInfo>();
            iteratorElement(PO_LIST, new ElementHandler<Field>() {
                public void handler(Element element) {
                    List children = element.getChildren("field");
                    for (Object field : children) {

                    }
                    FieldInfo fieldInfo = new FieldInfo();
                    //fieldInfo.setName(element.getat);
                }
            });
        } else {
            coder.setGenPo(false);
        }
    }

    public <T> T getValue(String xpath, Class<T> clazz) {
        try {
            Object obj = XPath.selectSingleNode(rootElement, xpath);
            if (obj != null) {
                if (clazz == String.class) {
                    return clazz.getConstructor(String.class).newInstance(obj.toString());
                } else if (clazz == Boolean.class || clazz == boolean.class) {
                    return clazz.getConstructor(Boolean.class).newInstance(obj.toString());
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    private void iteratorElement(String xpath, ElementHandler elementHandler) throws JDOMException {
        List list = XPath.selectNodes(rootElement, xpath);
        if (null != list) {
            for (Object object : list) {
                elementHandler.handler((Element) object);
            }
        }
    }

    public void setData(String key, Object value) {
        if (null != key && key.trim().length() > 0 && null != value) {
            dataMap.put(key, value);
        }
    }
}
