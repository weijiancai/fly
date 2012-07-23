package com.fly.sys.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class UStringTest {
    @Test
    public void testTableNameToClassName() throws Exception {
        String tableName = "cgdd_sp";
        assertThat(UString.tableNameToClassName(tableName), equalTo("CgddSp"));

        tableName = "sys_project_define";
        assertThat(UString.tableNameToClassName(tableName), equalTo("ProjectDefine"));

        tableName = "sp";
        assertThat(UString.tableNameToClassName(tableName), equalTo("Sp"));
    }
}
