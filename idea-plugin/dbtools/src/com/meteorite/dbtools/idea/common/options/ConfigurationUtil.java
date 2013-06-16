package com.meteorite.dbtools.idea.common.options;

import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ConfigurationUtil {
    public static void writeConfiguration(Element element, Configuration configuration) throws WriteExternalException {
        String elementName = configuration.getConfigElementName();
        if (elementName != null) {
            Element childElement = new Element(elementName);
            element.addContent(childElement);
            configuration.writeConfiguration(childElement);
        }
    }


    public static void readConfiguration(Element element, Configuration configuration) throws InvalidDataException {
        String elementName = configuration.getConfigElementName();
        if (elementName != null) {
            Element childElement = element.getChild(elementName);
            if (childElement != null) {
                configuration.readConfiguration(childElement);
            }
        }
    }
}
