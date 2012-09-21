package com.fly.sys.persist.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class MapElements {
    @XmlElement
    public String key;
    @XmlElement
    public Object value;

    private MapElements() {} // Required by JAXB

    public MapElements(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
