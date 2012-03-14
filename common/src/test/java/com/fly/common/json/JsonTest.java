package com.fly.common.json;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 */
public class JsonTest {
    @Test
    public void testToList() throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ClassDef.id", "10");
        map.put("ClassDef.name", "Module");
        map.put("ClassDef.desc", "The module Class");
        list.add(map);

        String jsonStr = Json.toJsonString(list);
        assertThat(Json.toList(jsonStr), IsEqual.equalTo(list));
    }
}
