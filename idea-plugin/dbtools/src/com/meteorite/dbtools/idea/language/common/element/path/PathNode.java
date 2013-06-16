package com.meteorite.dbtools.idea.language.common.element.path;

import com.meteorite.dbtools.idea.language.common.element.ElementType;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface PathNode {
    PathNode getParent();

    int getIndexInParent();

    ElementType getElementType();

    PathNode getRootPathNode();

    boolean isRecursive();

    boolean isRecursive(ElementType elementType);

    void detach();
}
