package com.fly.base.persist.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class MapAdapter extends XmlAdapter<MapElements[], Map<String, Object>> {
    @Override
    public Map<String, Object> unmarshal(MapElements[] mapElementses) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        for (MapElements mapElements : mapElementses) {
            map.put(mapElements.key, mapElements.value);
        }

        return map;
    }

    @Override
    public MapElements[] marshal(Map<String, Object> map) throws Exception {
        MapElements[] mapElements = new MapElements[map.size()];

        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());
        }

        return mapElements;
    }
}
