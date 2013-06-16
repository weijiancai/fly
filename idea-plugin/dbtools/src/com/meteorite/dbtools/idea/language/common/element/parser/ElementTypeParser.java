package com.meteorite.dbtools.idea.language.common.element.parser;

import com.intellij.lang.PsiBuilder;
import com.meteorite.dbtools.idea.language.common.ParseException;
import com.meteorite.dbtools.idea.language.common.element.ElementType;
import com.meteorite.dbtools.idea.language.common.element.path.ParsePathNode;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface ElementTypeParser<T extends ElementType> {
    ParseResult parse(ParsePathNode parentNode, PsiBuilder builder, boolean optional, int depth, long timestamp) throws ParseException;

    T getElementType();
}
