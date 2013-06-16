package com.meteorite.dbtools.idea.code.common.style.formatting;

import com.intellij.formatting.Indent;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum IndentDefinition implements FormattingAttribute<Indent> {
    NORMAL(new Loader<Indent>() {
        @Override
        Indent load() {
            return Indent.getNormalIndent();
        }
    }),
    CONTINUE(new Loader<Indent>() {
        @Override
        Indent load() {
            return Indent.getContinuationIndent();
        }
    }),
    NONE(new Loader<Indent>() {
        @Override
        Indent load() {
            return Indent.getNoneIndent();
        }
    }),
    ABSOLUTE_NONE(new Loader<Indent>() {
        @Override
        Indent load() {
            return Indent.getAbsoluteNoneIndent();
        }
    })
    ;

    private Indent value;
    private Loader<Indent> loader;

    private IndentDefinition(Loader<Indent> loader) {
        this.loader = loader;
    }

    @Override
    public Indent getValue() {
        if (value == null && loader != null) {
            value = loader.load();
            loader = null;
        }
        return value;
    }
}
