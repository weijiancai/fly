package com.meteorite.dbtools.idea.language.common.element;

import com.intellij.psi.TokenType;
import com.meteorite.dbtools.idea.code.common.completion.options.filter.CodeCompletionFilterSettings;
import com.meteorite.dbtools.idea.language.common.element.path.PathNode;

import java.util.Set;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface LeafElementType extends ElementType {
    void setTokenType(TokenType tokenType);

    TokenType getTokenTpe();

    void setOptional(boolean optional);

    boolean isOptional();

    void registerLeaf();

    boolean isIdentifier();

    boolean isSameAs(LeafElementType leaf);

    Set<LeafElementType> getNextPossibleLeafs(PathNode pathNode, CodeCompletionFilterSettings filterSettings);
}
