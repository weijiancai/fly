package com.meteorite.dbtools.idea.code.common.style.formatting;

import com.intellij.psi.PsiElement;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface FormattingProviderPsiElement extends PsiElement {
    FormattingAttributes getFormattingAttributes();
    FormattingAttributes getFormattingAttributesRecursive(boolean left);
}
