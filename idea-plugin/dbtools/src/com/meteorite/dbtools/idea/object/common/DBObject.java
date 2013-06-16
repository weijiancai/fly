package com.meteorite.dbtools.idea.object.common;

import com.intellij.psi.PsiNamedElement;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import com.meteorite.dbtools.idea.code.common.lookup.LookupValueProvider;
import com.meteorite.dbtools.idea.common.content.DynamicContentElement;
import com.meteorite.dbtools.idea.language.common.DBLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DBObject extends BrowserTreeNode, PsiNamedElement, DynamicContentElement, LookupValueProvider {
    List<DBObject> EMPTY_LIST = new ArrayList<DBObject>();

    DBObjectType getObjectType();
    boolean isOfType(DBObjectType objectType);

    DBLanguageDialect getLanguageDialect(DBLanguage language);

    DBObjectAttribute[] getObjectAttributes();
    DBObjectAttribute getNameAttribute();

    @NotNull
    String getName();
    String getQuotedName(boolean quoteAlways);
    boolean needsNameQuoting();
    String getQualifiedName();
    String getQualifiedNameWithType();
    String getQualifiedNameWithConnectionId();
    String getNavigationTooltipText();
    String getTypeName();
    Icon getIcon();
    Icon getOriginalIcon();

    DBUser getOwner();
    DBSchema getSchema();
    DBObject getParentObject();
    DBObjectBundle getObjectBundle();

    @Nullable
    DBObject getDefaultNavigationObject();
    List<DBObject> getChildObjects(DBObjectType objectType);
    DBObject getChildObject(DBObjectType objectType, String name, boolean lookupHidden);
    DBObject getChildObject(String name, boolean lookupHidden);

    List<DBObjectNavigationList> getNavigationLists();

    @Nullable
    DBObjectListContainer getChildObjects();

    @Nullable
    DBObjectRelationListContainer getChildObjectRelations();
    String extractDDL() throws SQLException;

    DBObject getUndisposedElement();

    DBObjectProperties getProperties();
    DBOperationExecutor getOperationExecutor();

    @NotNull
    DatabaseObjectFile getVirtualFile();
    List<PresentableProperty> getPresentableProperties();
    EnvironmentType getEnvironmentType();

    DBObjectIdentifier getIdentifier();
}
