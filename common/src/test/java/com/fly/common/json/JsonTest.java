package com.fly.common.json;

import com.alibaba.fastjson.JSON;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
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

    @Test
    public void testToMap() throws Exception {
        String jsonStr = "{id : 1, name : zhangsan, age : 24}";
        String result = "{ age = 24, id = 1,  name = zhangsan}";
        //assertThat(Json.toMap(jsonStr).toString(), equalTo(result));

        jsonStr = "{address:{city:beijing,street:zheng bai qi 20 号}}";
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> subMap = new HashMap<String, Object>();
        subMap.put("city", "beijing");
        subMap.put("street", "zheng bai qi 20 号");
        map.put("address", subMap);
        assertThat(Json.toMap(jsonStr), equalTo(map));
        
        jsonStr = "{addr:[{city:\"beijing\"},{city:\"shanghai\"}]}";
        map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        subMap = new HashMap<String, Object>();
        subMap.put("city", "beijing");
        list.add(subMap);
        subMap = new HashMap<String, Object>();
        subMap.put("city", "shanghai");
        list.add(subMap);
        map.put("addr", list);
        assertThat(JSON.parseObject(jsonStr), equalTo(map));
    }
}
