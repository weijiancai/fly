package com.meteorite.dbtools.idea.language.common.element.util;

import com.meteorite.dbtools.idea.common.util.EnumerationUtil;
import com.meteorite.dbtools.idea.language.common.psi.IdentifierPsiElement;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum IdentifierRole {
    DEFINITION,
    REFERENCE,
    UNKNOWN,
    ALL;

    public boolean matches(IdentifierPsiElement identifierPsiElement) {
        return false;
    }

    public boolean isOneOf(IdentifierRole... roles) {
        return EnumerationUtil.isOneOf(this, roles);
    }
}
