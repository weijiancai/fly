package com.fly.sys.clazz;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author weijiancai
 */
public class ClassDefineTest {
    @Test
    public void testSaveClassDef() {
        Map<String, Object> classMap = new HashMap<String, Object>();
        String classDefId = UUID.randomUUID().toString().replaceAll("-", "");
        classMap.put("id", classDefId);
        classMap.put("name", "ClassDefine"); // 类名
        classMap.put("cname", "类定义"); // 中文名
        classMap.put("author", "weijiancai"); // 作者
        classMap.put("class_desc", "类定义信息"); // 类描述
        classMap.put("version", "1.0");  // 版本

        //ClassDefManager.save(classMap, "sys_class_define");

        Map<String, Object> formMap = new HashMap<String, Object>();
        String formId = UUID.randomUUID().toString().replaceAll("-", "");
        formMap.put("id", formId);
        formMap.put("class_id", classDefId);
        formMap.put("name", "default");
        formMap.put("col_count", 3);
        formMap.put("col_width", 50);
        formMap.put("label_gap", 5);
        formMap.put("field_gap", 10);
        formMap.put("isDefault", 1);

        //ClassDefManager.save(formMap, "sys_class_form");
        
        /*Map<String, Map<String, Object>> clazzMap = new HashMap<String, Map<String, Object>>();
        clazzMap.put("ClassDefine", classMap);
        

        Map<String, String> params = new HashMap<String, String>();
        params.put("classDef", "ClassDefine");
        params.put("save", Json.toJsonString(clazzMap));*/
    }

    @Test
    public void test_demo_0() throws Exception {
        String sql = "SELECT UUID();";

        // parser得到AST
        SQLStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> stmtList = parser.parseStatementList(); //

        // 将AST通过visitor输出
        StringBuilder out = new StringBuilder();
        MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);

        for (SQLStatement stmt : stmtList) {
            stmt.accept(visitor);
            out.append(";");
        }

        System.out.println(out.toString());
    }
}
