package com.meteorite.dbtools.idea.code.common.style.formatting;

import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.Wrap;
import com.meteorite.dbtools.idea.common.util.CommonUtil;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class FormattingDefinition {
    public static final FormattingDefinition LINE_BREAK_BEFORE = new FormattingDefinition();

    private WrapDefinition wrap;
    private IndentDefinition indent;
    private SpacingDefinition spacingBefore;
    private SpacingDefinition spacingAfter;
    private FormattingAttributes attributes;

    public FormattingDefinition() {
    }

    public FormattingDefinition(WrapDefinition wrap, IndentDefinition indent, SpacingDefinition spacingBefore, SpacingDefinition spacingAfter) {
        this.wrap = wrap;
        this.indent = indent;
        this.spacingBefore = spacingBefore;
        this.spacingAfter = spacingAfter;
    }

    protected FormattingDefinition(FormattingDefinition attributes) {
        wrap = attributes.wrap;
        indent = attributes.indent;
        spacingBefore = attributes.spacingBefore;
        spacingAfter = attributes.spacingAfter;
    }

    public void merge(FormattingDefinition defaults) {
        wrap = CommonUtil.nvln(wrap, defaults.wrap);
        indent = CommonUtil.nvln(indent, defaults.indent);
        spacingBefore = CommonUtil.nvln(spacingBefore, defaults.spacingBefore);
        spacingAfter = CommonUtil.nvln(spacingAfter, defaults.spacingAfter);
    }

    public FormattingAttributes getAttributes() {
        if (attributes == null) {
            Wrap wrap = this.wrap == null ? null : this.wrap.getValue();
            Indent indent = this.indent == null ? null : this.indent.getValue();
            Spacing spacingBefore = this.spacingBefore == null ? null : this.spacingBefore.getValue();
            Spacing spacingAfter = this.spacingAfter == null ? null : this.spacingAfter.getValue();
            attributes = new FormattingAttributes(wrap, indent, spacingBefore, spacingAfter);
        }
        return attributes;
    }

    public boolean isEmpty() {
        return wrap == null && indent == null && spacingBefore == null && spacingAfter == null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        if (wrap != null) result.append("wrap=").append(wrap).append(" ");
        if (indent != null) result.append("indent=").append(indent).append(" ");
        if (spacingBefore != null) result.append("spacingBefore=").append(spacingBefore).append(" ");
        if (spacingAfter != null) result.append("spacingAfter=").append(spacingAfter).append(" ");

        return result.toString();
    }
}
