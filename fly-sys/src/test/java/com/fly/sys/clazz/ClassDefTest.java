package com.fly.sys.clazz;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ClassDefTest {
    @Test
    public void testSaveClassDef() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("classDef", "ClassDef");
        params.put("name", "Module"); // 类名
        params.put("cname", "模块"); // 中文名
        params.put("author", "weijiancai"); // 作者
        params.put("desc", "系统模块"); // 类描述
        params.put("version", "1.0");  // 版本

        ClassDefManager.saveClassDef(params);
    }
}
