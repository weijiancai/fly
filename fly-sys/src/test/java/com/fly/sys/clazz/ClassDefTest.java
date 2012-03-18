package com.fly.sys.clazz;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.fly.common.json.Json;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author weijiancai
 */
public class ClassDefTest {
    @Test
    public void testSaveClassDef() {
        Map<String, Object> saveValueMap = new HashMap<String, Object>();
        saveValueMap.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
        saveValueMap.put("name", "ClassDef"); // 类名
        saveValueMap.put("cname", "类定义"); // 中文名
        saveValueMap.put("author", "weijiancai"); // 作者
        saveValueMap.put("`desc`", "类定义信息"); // 类描述
        saveValueMap.put("version", "1.0");  // 版本
        saveValueMap.put("col_Width", 50);
        saveValueMap.put("col_Count", 2);
        
        Map<String, Map<String, Object>> clazzMap = new HashMap<String, Map<String, Object>>();
        clazzMap.put("ClassDef", saveValueMap);
        

        Map<String, String> params = new HashMap<String, String>();
        params.put("classDef", "ClassDef");
        params.put("save", Json.toJsonString(clazzMap));

        ClassDefManager.saveClassDef(saveValueMap);
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
