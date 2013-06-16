package com.meteorite.dbtools.idea.language.common;

import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class DBLanguageTokenTypeBundle {
    protected final Logger log = Logger.getInstance(getClass().getName());
    private Language language;
    private SimpleTokenType[] keywords;
}
