package com.meteorite.dbtools.idea.language.common.element.path;

import com.meteorite.dbtools.idea.language.common.element.ElementType;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BasicPathNode implements PathNode {
    private int indexInParent;
    private PathNode parent;
    private ElementType elementType;

    public BasicPathNode(int indexInParent, PathNode parent, ElementType elementType) {
        this.indexInParent = indexInParent;
        this.parent = parent;
        this.elementType = elementType;
    }

    @Override
    public PathNode getParent() {
        return parent;
    }

    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    @Override
    public int getIndexInParent() {
        return indexInParent;
    }

    public void setIndexInParent(int indexInParent) {
        this.indexInParent = indexInParent;
    }

    @Override
    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public PathNode getRootPathNode() {
        PathNode pathNode = parent;
        while (pathNode != null) {
            PathNode parentPathNode = pathNode.getParent();
            if (parentPathNode == null) {
                return parentPathNode;
            }
            pathNode = parentPathNode;
        }
        return this;
    }

    @Override
    public boolean isRecursive() {
        PathNode node = this.getParent();
        while (node != null) {
            if (elementType == node.getElementType()) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    @Override
    public boolean isRecursive(ElementType elementType) {
        if (getElementType() == elementType) {
            return true;
        }
        PathNode node = this.getParent();
        while (node != null) {
            if (elementType == node.getElementType()) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        PathNode parent = this;
        while (parent != null) {
            buffer.insert(0, '/');
            buffer.insert(0, parent.getElementType().getId());
            parent = parent.getParent();
        }
        return buffer.toString();
    }

    @Override
    public void detach() {
        parent = null;
    }
}
