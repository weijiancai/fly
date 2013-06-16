package com.meteorite.dbtools.idea.common.options;

import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface PersistentConfiguration {
    void readConfiguration(Element element) throws InvalidDataException;
    void writeConfiguration(Element element) throws WriteExternalException;
}
