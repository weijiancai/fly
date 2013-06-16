package com.meteorite.dbtools.idea.language.common;

import com.intellij.lang.Language;
import com.intellij.lang.LanguageDialect;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class DBLanguageDialect extends LanguageDialect implements DBFileElementTypeProvider {
    public DBLanguageDialect(@NonNls @NotNull String id, @NotNull Language baseLanguage) {
        super(id, baseLanguage);
    }
}
