package com.meteorite.dbtools.idea.language.common.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.meteorite.dbtools.idea.code.common.style.formatting.FormattingAttributes;
import com.meteorite.dbtools.idea.code.common.style.formatting.FormattingProviderPsiElement;
import com.meteorite.dbtools.idea.language.common.element.ElementType;
import com.meteorite.dbtools.idea.language.common.element.util.ElementTypeAttribute;
import org.jetbrains.annotations.NotNull;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class BasePsiElement extends ASTWrapperPsiElement implements ItemPresentation, FormattingProviderPsiElement {
    private ElementType elementType;
//    private DBVirtualObject underlyingObject;
    private boolean isScopeIsolation;
    private boolean isScopeDemarcation;
    private FormattingAttributes formattingAttributes;

    public BasePsiElement(@NotNull ASTNode astNode, ElementType elementType) {
        super(astNode);
        this.elementType = elementType;
        this.isScopeIsolation = elementType.is(ElementTypeAttribute.SCOPE_ISOLATION);
        this.isScopeDemarcation = elementType.is(ElementTypeAttribute.SCOPE_DEMARCATION);
    }
}
