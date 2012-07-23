package com.fly.sys.dict;

import com.fly.sys.db.DBManager;
import com.fly.sys.db.DbmsRowMapperFactory;
import com.fly.sys.db.JdbcTemplate;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class CodeManager {
    public static List<DictCategory> categoryList;
    public static Map<String, DictCode> codeIdMap = new HashMap<String, DictCode>();

    static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() throws Exception {
        Connection conn = DBManager.getSysConn();

        try {
            conn.setAutoCommit(false);

            JdbcTemplate template = new JdbcTemplate(conn);
            // 查询sys_dz_category
            String sql = "SELECT * FROM sys_dz_category";
            categoryList = template.query(sql, DbmsRowMapperFactory.getCategory());
            // 查询sys_dz_code
            for (DictCategory category : categoryList) {
                // 查询sys_dbms_table
                sql = "SELECT * FROM sys_dz_code WHERE category_id=?";
                List<DictCode> codeList = template.query(sql, DbmsRowMapperFactory.getCode(category), category.getId());
                category.setCodeList(codeList);
                for (DictCode code : codeList) {
                    codeIdMap.put(code.getId(), code);
                }
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (null != conn) {
                conn.rollback();
            }
        } finally {
            if (null != conn) {
                conn.close();
            }
        }
    }

    public static DictCode getCodeById(String codeId) {
        return codeIdMap.get(codeId);
    }
}
