package com.wjc.autoproject;

import org.jdom.Element;


/**
 * @author weijiancai
 */
public interface ElementHandler<T> {
    void handler(Element element);
}
