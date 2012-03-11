package com.fly.db.util;

import org.junit.Test;

/**
 * @author weijiancai
 */
public class CharsetConvertTest {
    @Test
    public void testConvertFile() throws Exception {
        CharsetConvert convert = new CharsetConvert("GBK", "UTF-8");
        convert.convert("D:\\workspace\\fly\\db\\src\\main\\java\\com\\fly\\base\\db\\util\\StringUtil.java1");
    }
}
