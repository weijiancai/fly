package com.meteorite.dbtools.idea.language.common.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import com.meteorite.dbtools.idea.language.common.element.IdentifierElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class IdentifierPsiElement extends LeafPsiElement implements PsiNamedElement {

    public IdentifierPsiElement(ASTNode astNode, IdentifierElementType elementType) {
        super(astNode, elementType);

    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return null;
    }
}
