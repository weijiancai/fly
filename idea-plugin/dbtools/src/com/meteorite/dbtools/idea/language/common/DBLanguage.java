package com.meteorite.dbtools.idea.language.common;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NonNls;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class DBLanguage<D extends DBLanguageDialect> extends Language implements DBFileElementTypeProvider {
    private D[] languageDialects;
    private SharedTokenTypeBundle sharedTokenTypes;

    protected DBLanguage(final @NonNls String id, final @NonNls String... mimeTypes){
        super(id, mimeTypes);
        sharedTokenTypes = new SharedTokenTypeBundle(this);
    }
}
