package com.fly.sys.io;

import org.junit.Test;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class CutFileTest {
    @Test
    public void testRead() throws Exception {
        String file = "C:\\Users\\WangXinghao\\Desktop\\onix数据\\wiley.1.20120511.xml";
        long start = System.currentTimeMillis();
        CutFile cutFile = new CutFile();
        cutFile.read(file);
        System.out.println((System.currentTimeMillis() - start)/1000);
    }
}
