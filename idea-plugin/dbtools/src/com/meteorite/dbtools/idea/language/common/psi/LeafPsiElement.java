package com.meteorite.dbtools.idea.language.common.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import com.meteorite.dbtools.idea.code.common.style.formatting.FormattingAttributes;
import com.meteorite.dbtools.idea.language.common.element.ElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class LeafPsiElement extends BasePsiElement implements PsiReference {
    public LeafPsiElement(@NotNull ASTNode node, ElementType elementType) {
        super(node, elementType);
    }

    @Override
    public FormattingAttributes getFormattingAttributes() {
        return null;
    }

    @Override
    public FormattingAttributes getFormattingAttributesRecursive(boolean left) {
        return null;
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return null;
    }

    @Nullable
    @Override
    public String getLocationString() {
        return null;
    }

    @Nullable
    @Override
    public Icon getIcon(boolean unused) {
        return null;
    }

    @Override
    public PsiElement getElement() {
        return null;
    }

    @Override
    public TextRange getRangeInElement() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return null;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return null;
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        return null;
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        return null;
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return false;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
