package com.fly.sys.clazz;

import com.fly.sys.util.UString;

/**
 * 类管理器
 *
 * @author weijiancai
 */
public class ClassManager {
    public static ClassField getClassFieldById(String classFieldId) {
        return ClassDefLoader.classFieldIdMap.get(classFieldId);
    }

    public static ClassDefine getClassDefine(String className) {
        if (UString.isEmpty(className)) {
            return null;
        }

        return ClassDefLoader.loadClassDef(className.toLowerCase());
    }

    public static ClassDefine getClassDefineById(String classId) {
        return ClassDefLoader.classIdMap.get(classId);
    }
}
