package com.fly.sys.clazz;

import com.fly.sys.R;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 类定义装载器，从数据库中装载类定义信息
 *
 * @author weijiancai
 */
public class ClassDefLoader {
    private static Map<String, Object> cache = new HashMap<String, Object>();

    static {
        // 存储类定义
        ClassDef def = new ClassDef();
        def.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        def.setName("ClassDef");
        def.setAuthor("weijiancai");
        def.setCname("类定义");
        def.setVersion("1.0");

        Map<String, Object> classDefMap = new HashMap<String, Object>();
        classDefMap.put(R.classdef.name, "ClassDef");
        classDefMap.put(R.classdef.cname, "类定义");
        classDefMap.put(R.classdef.author, "weijiancai");
        classDefMap.put(R.classdef.class_desc, "");
        classDefMap.put(R.classdef.version, "1.0");
        classDefMap.put(R.classdef.col_count, 2);
        classDefMap.put(R.classdef.col_width, 90);

        cache.put("ClassDef", classDefMap);
    }

    /**
     * 装载类定义信息
     *
     * @param className 类名
     * @return 返回类定义信息
     */
    public static Object loadClassDef(String className) {
        if (cache.containsKey(className)) {
            return cache.get(className);
        } else {
            // 从数据库中读取类定义信息
            if ("ClassDef".equals(className)) {

            }
        }
        return null;
    }
}
