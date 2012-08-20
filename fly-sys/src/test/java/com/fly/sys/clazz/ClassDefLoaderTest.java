package com.fly.sys.clazz;

import com.alibaba.fastjson.JSON;
import com.fly.sys.config.SysInfo;
import com.fly.sys.db.DBManager;
import com.fly.sys.dict.CodeManager;
import com.fly.sys.vo.ClassDefineVO;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 */
public class ClassDefLoaderTest {
    @Test
    public void testInitClassDefFromTable() throws Exception {
        ClassDefine classDef = new ClassDefine();
        classDef.setAuthor("");
        classDef.setCname("数据库管理系统定义");
        classDef.setDesc("");
        classDef.setName("DbmsDefine");
        classDef.setSuperClass("");
        classDef.setVersion("");


        //ClassDefLoader.initClassDefFromTable("sys_dbms_define");
        ClassDefine result = ClassDefManager.getClassDef("DbmsDefine");
        assertThat(result.getName(), IsEqual.equalTo(classDef.getName()));
        assertThat(result.getCname(), IsEqual.equalTo(classDef.getCname()));

        // 判断字段信息
        assertThat(result.getFieldList().size(), equalTo(10));
        Map<String, ClassField> fieldMap = result.getFieldMap();
        assertThat(fieldMap.size(), equalTo(10));

    }

    @Test
    public void testInitClassDef() throws Exception {
        SysInfo.setDbmsInit(false);
        SysInfo.setDictInit(false);
        SysInfo.setClassDefInit(false);
        DBManager.init();
        CodeManager.init();
        ClassDefLoader.init();

//        ClassDefine classDefine = ClassDefLoader.classIdMap.values().iterator().next();
        ClassDefine classDefine = ClassDefLoader.loadClassDef("ClassDefine".toLowerCase());
//        System.out.println(Json.toJsonString(classDefine));
        System.out.println(JSON.toJSONString(ClassDefineVO.getInstance(classDefine), true));
    }
}
