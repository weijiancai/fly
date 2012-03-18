package com.fly.sys.clazz;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 类定义装载器，从数据库中装载类定义信息
 *
 * @author weijiancai
 */
public class ClassDefLoader {
    private Map<String, ClassDef> cache = new HashMap<String, ClassDef>();

    static {
        // 存储类定义
        ClassDef def = new ClassDef();
        def.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        def.setName("ClassDef");
        def.setAuthor("weijiancai");
        def.setCname("类定义");
        def.setVersion("1.0");
        def.setColCount(2);
        def.setColWidth(50);

        Field idField = new Field();
    }

    /**
     * 装载类定义信息
     *
     * @param className 类名
     * @return 返回类定义信息
     */
    public static ClassDef loadClassDef(String className) {

        return null;
    }
}
