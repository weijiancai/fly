package com.meteorite.dbtools.idea.language.common.element;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.meteorite.dbtools.idea.code.common.style.formatting.FormattingDefinition;
import com.meteorite.dbtools.idea.language.common.DBLanguage;
import com.meteorite.dbtools.idea.language.common.DBLanguageDialect;
import com.meteorite.dbtools.idea.language.common.element.lookup.ElementTypeLookupCache;
import com.meteorite.dbtools.idea.language.common.element.parser.ElementTypeParser;
import com.meteorite.dbtools.idea.language.common.element.util.ElementTypeAttribute;
import com.meteorite.dbtools.idea.language.common.element.util.ElementTypeAttributeBundle;
import com.meteorite.dbtools.idea.object.common.DBObjectType;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface ElementType {
    String getId();

    String getDescription();

    String getDebugName();

    Icon getIcon();

    ElementType getParent();

    DBLanguage getLanguage();

    DBLanguageDialect getLanguageDialect();

    ElementTypeLookupCache getLookupCache();

    ElementTypeParser getParser();

    boolean is(ElementTypeAttribute attribute);

    boolean isLeaf();

    boolean isVirtualObject();

    boolean isVirtualObjectInsideLookup();

    DBObjectType getVirtualObjectType();

    PsiElement createPsiElement(ASTNode astNode);

    ElementTypeBundle getElementTypeBundle();

    void registerVirtualObject(DBObjectType objectType);

    FormattingDefinition getFormatting();

    void setDefaultFormatting(FormattingDefinition defaults);

    ElementTypeAttributeBundle getAttributes();
}
