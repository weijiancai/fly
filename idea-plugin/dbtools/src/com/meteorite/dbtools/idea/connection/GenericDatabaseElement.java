package com.meteorite.dbtools.idea.connection;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.common.content.DynamicContent;
import com.meteorite.dbtools.idea.common.content.DynamicContentType;
import sun.jdbc.odbc.ee.ConnectionHandler;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface GenericDatabaseElement {
    Project getProject();
    ConnectionHandler getConnectionHandler();
    GenericDatabaseElement getUndiposedElement();
    DynamicContent getDynamicContent(DynamicContentType dynamicContentType);
}
