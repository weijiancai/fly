package com.fly.base.persist.xml;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class MapBean {
    private HashMap<String, Object> map;

    @XmlJavaTypeAdapter(MapAdapter.class)
    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }
}
