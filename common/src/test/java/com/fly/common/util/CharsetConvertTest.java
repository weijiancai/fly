package com.fly.common.util;

import org.junit.Test;

/**
 * @author weijiancai
 */
public class CharsetConvertTest {
    @Test
    public void testConvertFile() throws Exception {
        CharsetConvert convert = new CharsetConvert("GBK", "UTF-8");
        convert.convert("D:\\workspace\\fly\\common\\src\\main\\java\\com\\fly\\common\\util\\NoJSON.java1");
    }
}
