package com.meteorite.dbtools.idea.language.common;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.meteorite.dbtools.idea.code.common.style.formatting.FormattingDefinition;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class SimpleTokenType extends IElementType implements TokenType {
    private String id;
    private String value;
    private String description;
    private boolean isSuppressibleReservedWord;
    private TokenTypeIdentifier tokenTypeIdentifier;
    private int idx;
    private int hashCode;
    private FormattingDefinition formatting;
}
