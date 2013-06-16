package com.meteorite.dbtools.idea.code.common.style.formatting;

import com.intellij.formatting.Spacing;
import com.meteorite.dbtools.idea.code.common.style.presets.CodeStylePreset;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum SpacingDefinition implements FormattingAttribute<Spacing> {
    NO_SPACE(new Loader<Spacing>() {
        @Override
        Spacing load() {
            return CodeStylePreset.SPACING_NO_SPACE;
        }
    }),
    ONE_SPACE(new Loader<Spacing>() {
        @Override
        Spacing load() {
            return CodeStylePreset.SPACING_ONE_SPACE;
        }
    }),
    LINE_BREAK(new Loader<Spacing>() {
        @Override
        Spacing load() {
            return CodeStylePreset.SPACING_LINE_BREAK;
        }
    }),
    ONE_LINE(new Loader<Spacing>() {
        @Override
        Spacing load() {
            return CodeStylePreset.SPACING_ONE_LINE;
        }
    }),
    MIN_LINE_BREAK(new Loader<Spacing>() {
        @Override
        Spacing load() {
            return CodeStylePreset.SPACING_MIN_LINE_BREAK;
        }
    }),
    MIN_ONE_LINE(new Loader<Spacing>() {
        @Override
        Spacing load() {
            return CodeStylePreset.SPACING_MIN_ONE_LINE;
        }
    })
    ;

    private Spacing value;
    private Loader<Spacing> loader;

    private SpacingDefinition(Loader<Spacing> loader) {
        this.loader = loader;
    }

    @Override
    public Spacing getValue() {
        if (value == null && loader != null) {
            value = loader.load();
            loader = null;
        }
        return value;
    }
}
