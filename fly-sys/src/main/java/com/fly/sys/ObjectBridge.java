package com.fly.sys;

import com.fly.sys.clazz.ClassDefine;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象桥，连接对象、文件、数据库、缓存
 * 类定义附属表sys-class-xml xml{root, filePath}
 * dbms的增删改查
 * 0. 从数据库表中创建ClassDef
 *   a. 插入一条类定义信息
 *   b. 插入一条sys_class_table
 *   c. 表名为数据库表
 *   d. updateOrSave表信息
 * 1. 先定义dbms的ClassDef
 * 2. 由ClassDef增删改查
 *
 * @author weijiancai
 */
public class ObjectBridge {
    private static Map<String, ClassDefine> classDef;
    private static Map<String, IPDB> pdbMap = new HashMap<String, IPDB>();
    private static Map<String, IPXML> pXmlMap = new HashMap<String, IPXML>();

    public static void setPdb(String className, IPDB pdb) {
        pdbMap.put(className, pdb);
    }

    public static void setPXml(String className, IPXML pXml) {
        pXmlMap.put(className, pXml);
    }

    public IPDB getPdb(String className) {
        return pdbMap.get(className);
    }

    public IPXML getPXml(String className) {
        return pXmlMap.get(className);
    }

    /**
     * 保存对象到数据库
     */
    public static void saveToDb() {

    }

    /**
     * 保存数据到xml
     */
    public static void saveToXml() {

    }
}
