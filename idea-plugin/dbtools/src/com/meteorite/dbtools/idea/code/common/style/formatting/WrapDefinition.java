package com.meteorite.dbtools.idea.code.common.style.formatting;

import com.intellij.formatting.Wrap;
import com.meteorite.dbtools.idea.code.common.style.presets.CodeStylePreset;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum WrapDefinition implements FormattingAttribute<Wrap> {
    NONE(new Loader<Wrap>() {
        @Override
        Wrap load() {
            return CodeStylePreset.WRAP_NONE;
        }
    })
    ;
    private Wrap value;
    private Loader<Wrap> loader;

    private WrapDefinition(Loader<Wrap> loader) {
        this.loader = loader;
    }

    @Override
    public Wrap getValue() {
        if (value == null && loader != null) {
            value = loader.load();
            loader = null;
        }
        return value;
    }
}
