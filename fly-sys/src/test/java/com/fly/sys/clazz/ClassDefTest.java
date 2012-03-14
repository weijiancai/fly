package com.fly.sys.clazz;

import com.fly.common.json.Json;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ClassDefTest {
    @Test
    public void testSaveClassDef() {
        Map<String, String> saveValueMap = new HashMap<String, String>();
        saveValueMap.put("ClassDef.name", "Module"); // 类名
        saveValueMap.put("ClassDef.cname", "模块"); // 中文名
        saveValueMap.put("ClassDef.author", "weijiancai"); // 作者
        saveValueMap.put("ClassDef.desc", "系统模块"); // 类描述
        saveValueMap.put("ClassDef.version", "1.0");  // 版本

        Map<String, String> params = new HashMap<String, String>();
        params.put("classDef", "ClassDef");
        params.put("save", Json.toJsonString(saveValueMap));

        ClassDefManager.saveClassDef(params);
    }
}
