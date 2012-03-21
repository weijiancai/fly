package com.fly.sys.clazz;

import org.junit.Test;

/**
 * @author weijiancai
 */
public class ClassDefManagerTest {
    @Test
    public void testGetClassDef() throws Exception {
        ClassDef def = ClassDefManager.getClassDef("ClassDef");
        System.out.println(def);
    }
}
