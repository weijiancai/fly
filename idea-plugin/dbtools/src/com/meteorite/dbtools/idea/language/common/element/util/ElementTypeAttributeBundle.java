package com.meteorite.dbtools.idea.language.common.element.util;

import gnu.trove.THashSet;

import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ElementTypeAttributeBundle {
    public static final ElementTypeAttributeBundle EMPTY = new ElementTypeAttributeBundle();

    private Set<ElementTypeAttribute> attributes = ElementTypeAttribute.EMPTY_LIST;

    public ElementTypeAttributeBundle() {
    }

    public ElementTypeAttributeBundle(String definition) throws ElementTypeDefinitionException {
        StringTokenizer tokenizer = new StringTokenizer(definition, ",");
        while (tokenizer.hasMoreElements()) {
            String attributeName = tokenizer.nextToken().trim();
            boolean found = false;
            for (ElementTypeAttribute attribute : ElementTypeAttribute.values()) {
                if (attribute.getName().equals(attributeName)) {
                    if (attributes == ElementTypeAttribute.EMPTY_LIST) {
                        attributes = new THashSet<ElementTypeAttribute>();
                    }
                    attributes.add(attribute);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new ElementTypeDefinitionException("Invalid element type attribute '" + attributeName + "'");
            }
        }
    }

    public boolean is(ElementTypeAttribute attribute) {
        return attributes.contains(attribute);
    }

    @Override
    public String toString() {
        return new ArrayList(attributes).toString();
    }
}
