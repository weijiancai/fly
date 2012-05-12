package com.fly.sys.clazz;

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
        return ClassDefLoader.loadClassDef(className.toLowerCase());
    }

    public static ClassDefine getClassDefineById(String classId) {
        return ClassDefLoader.classIdMap.get(classId);
    }
}
