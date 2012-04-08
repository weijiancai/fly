package com.fly.common.util;

import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class DomUtilTest {
    @Test
    public void testToList() throws Exception {
        File f = new File("D:\\workspace\\fly\\fly-sys\\src\\main\\resources\\system.xml");
        List<Map<String, String>> list = DomUtil.toList (f, "/system/modules/module");
        System.out.println(list);
    }
}
