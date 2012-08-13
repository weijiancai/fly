package com.fly.sys.util;

import org.junit.Test;

import java.net.URL;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class UFileTest {
    @Test
    public void testGetURL() throws Exception {
        URL url = UFile.getURL("/");
        System.out.println(url);
        System.out.println(UFile.class.getClassLoader().getResource("/"));
        System.out.println(UFile.toString("/system.xml"));
    }
}
