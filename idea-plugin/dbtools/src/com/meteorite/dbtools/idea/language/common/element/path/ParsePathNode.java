package com.meteorite.dbtools.idea.language.common.element.path;

import com.meteorite.dbtools.idea.language.common.element.ElementType;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ParsePathNode extends BasicPathNode {
    private int startOffset;

    public ParsePathNode(ElementType elementType, ParsePathNode parent, int startOffset, int indexInParent) {
        super(indexInParent, parent, elementType);
        this.startOffset = startOffset;
    }

    public ParsePathNode createVariant(int builderOffset, int position) {
        ParsePathNode vaiant = new ParsePathNode(getElementType(), getParent(), builderOffset, position);
        detach();
        return vaiant;
    }

    @Override
    public ParsePathNode getParent() {
        return (ParsePathNode) super.getParent();
    }

    public int getStartOffset() {
        return startOffset;
    }

    public boolean isRecursive() {
        ParsePathNode parseNode = this.getParent();
        while (parseNode != null) {
            if (parseNode.getElementType() == getElementType() &&
                    parseNode.getStartOffset() == getStartOffset()) {
                return true;
            }
            parseNode = parseNode.getParent();
        }
        return false;
    }
}
