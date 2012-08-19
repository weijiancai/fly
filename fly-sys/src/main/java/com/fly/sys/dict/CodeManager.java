package com.fly.sys.dict;

import com.fly.sys.config.SysInfo;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.DbmsPDBFactory;
import com.fly.sys.db.DbmsRowMapperFactory;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.util.Callback;
import com.fly.sys.util.DomUtil;
import com.fly.sys.util.UFile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class CodeManager {
    public static List<DictCategory> categoryList;
    public static Map<String, DictCategory> categoryIdMap = new HashMap<String, DictCategory>();
    public static Map<String, DictCategory> categoryNameMap = new HashMap<String, DictCategory>();
    public static Map<String, DictCode> codeIdMap = new HashMap<String, DictCode>();
    public static DictCategory rootCategory = new DictCategory();

    static {
        /*try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private static void initRootCategory() throws Exception {
        rootCategory.setId("rootCategory");
        rootCategory.setName("系统数据字典");
        rootCategory.setValid(true);
        rootCategory.setSortNum(0);
        categoryIdMap.put(rootCategory.getId(), rootCategory);
        Connection conn = DBManager.getSysConn();
        try {
            conn.setAutoCommit(false);
            JdbcTemplate template = new JdbcTemplate(conn);
            template.clearTable("sys_dz_category", "sys_dz_code");
            template.save(DbmsPDBFactory.getDictCategory(rootCategory));
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.rollback();
        }
    }

    public static void init() throws Exception {
        Connection conn = DBManager.getSysConn();

        try {
            conn.setAutoCommit(false);

            JdbcTemplate template = new JdbcTemplate(conn);

            if (SysInfo.isDictInit()) {
                // 查询sys_dz_category
                String sql = "SELECT * FROM sys_dz_category";
                categoryList = template.query(sql, DbmsRowMapperFactory.getCategory());
                // 查询sys_dz_code
                for (DictCategory category : categoryList) {
                    categoryIdMap.put(category.getId(), category);
                    categoryNameMap.put(category.getName(), category);
                    // 查询sys_dbms_table
                    sql = "SELECT * FROM sys_dz_code WHERE category_id=?";
                    List<DictCode> codeList = template.query(sql, DbmsRowMapperFactory.getCode(category), category.getId());
                    category.setCodeList(codeList);
                    for (DictCode code : codeList) {
                        codeIdMap.put(code.getId(), code);
                    }
                }
            } else {
                // 清空表
                initRootCategory();
                // 从System.xml文件中进行初始化
                initDict(template, "system");
                // 从Project.xml文件中进行初始化
                initDict(template, "project");
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

        // 初始化根数据字典列表
        rootCategory.setCodeList(getCodeListByTable("sys_dz_category", "name", "id"));
        SysInfo.setDictInit(true);
//        SysInfo.store();
    }

    private static void initDict(JdbcTemplate template, String fileName) throws Exception {
        List<Map<String, String>> list = DomUtil.toList(UFile.toString("/" + fileName + ".xml"), "/" + fileName + "/dict/categories/category");

        DictCategory category;
        DictCode code;
        for (Map<String, String> map : list) {
            category = new DictCategory();
            category.setName(map.get("name"));
            category.setSortNum(Integer.parseInt(map.get("sortNum")));
            category.setValid(true);

            // 保存
            template.save(DbmsPDBFactory.getDictCategory(category));
            categoryIdMap.put(category.getId(), category);
            categoryNameMap.put(category.getName(), category);

            List<Map<String, String>> mapList = DomUtil.toList(UFile.toString("/" + fileName + ".xml"), "/" + fileName + "/dict/categories/category[@name=\"" + category.getName() + "\"]/code");
            List<DictCode> codeList = new ArrayList<DictCode>();
            for (Map<String, String> codeMap : mapList) {
                code = new DictCode();
                code.setCategory(category);
                code.setName(codeMap.get("name"));
                code.setValue(codeMap.get("value"));
                code.setSortNum(Integer.parseInt(codeMap.get("sortNum")));
                code.setValid(true);

                template.save(DbmsPDBFactory.getDictCode(code));
                codeList.add(code);
                codeIdMap.put(code.getId(), code);
            }
            category.setCodeList(codeList);
        }
    }

    public static DictCategory getDictCategoryById(String categoryId) {
        return categoryIdMap.get(categoryId);
    }

    public static DictCategory getDictCategoryByName(String categoryName) {
        return categoryNameMap.get(categoryName);
    }

    public static DictCode getCodeById(String codeId) {
        return codeIdMap.get(codeId);
    }

    public static List<DictCode> getCodeListByTable(String tableName, final String nameCol, final String valueCol) throws Exception {
        final List<DictCode> list = new ArrayList<DictCode>();

        JdbcTemplate template = new JdbcTemplate(DBManager.getSysConn());
        template.query("SELECT * FROM " + tableName, new Callback<ResultSet>() {
            @Override
            public void call(ResultSet rs, Object... obj) throws Exception {
                DictCode code = new DictCode();
                code.setName(rs.getString(nameCol));
                code.setValue(rs.getString(valueCol));
                list.add(code);
            }
        });
        template.close();

        return list;
    }
}
