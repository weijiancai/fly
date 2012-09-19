package com.fly.sys.util.json;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class JsonTest {
    @Test
    public void testToList() throws Exception {

    }

    @Test
    public void testToMap() throws Exception {
        Map<String, Object> actual = new HashMap<String, Object>();
        actual.put("name", "zhangsan");
        actual.put("age", 20);
        String jsonStr = Json.toJsonString(actual);

        Map<String, Object> map = Json.toMap(jsonStr);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
