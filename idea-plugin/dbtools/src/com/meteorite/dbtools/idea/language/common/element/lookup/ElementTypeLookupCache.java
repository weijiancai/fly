package com.meteorite.dbtools.idea.language.common.element.lookup;

import com.intellij.psi.TokenType;
import com.meteorite.dbtools.idea.language.common.element.ElementType;
import com.meteorite.dbtools.idea.language.common.element.LeafElementType;
import com.meteorite.dbtools.idea.language.common.element.util.IdentifierRole;
import com.meteorite.dbtools.idea.language.common.element.util.IdentifierType;
import com.meteorite.dbtools.idea.object.common.DBObjectType;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface ElementTypeLookupCache<T extends ElementType> {
    void registerLeaf(LeafElementType leaf, ElementType pathChild);

    void registerVirtualObject(DBObjectType objectType);

    boolean containsLeaf(LeafElementType leafElementType);

    boolean containsToken(TokenType tokenType);

    boolean containsIdentifier(DBObjectType objectType, IdentifierType identifierType, IdentifierRole identifierRole);
}
