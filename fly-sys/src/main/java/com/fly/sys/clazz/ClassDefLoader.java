package com.fly.sys.clazz;

import com.fly.common.util.StringUtil;
import com.fly.sys.config.SysInfo;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsSchema;
import com.fly.sys.db.meta.DbmsTable;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类定义装载器，从数据库中装载类定义信息
 *
 * @author weijiancai
 */
public class ClassDefLoader {
    private static Map<String, ClassDefine> cache = new HashMap<String, ClassDefine>();

    public static Map<String, ClassField> classFieldIdMap = new HashMap<String, ClassField>();
    public static Map<String, ClassDefine> classIdMap = new HashMap<String, ClassDefine>();
    private static int classSortNum = 10;

    static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() throws Exception {
        Connection conn = DBManager.getConn();
        conn.setAutoCommit(false);
        JdbcTemplate template = new JdbcTemplate(conn);
        try {
            if (SysInfo.isClassDefInit()) { // ClassDef 已经初始化
                String sql = "SELECT * FROM sys_class_define";
                List<ClassDefine> classList = template.query(sql, ClassRowMapperFactory.getClassDefine());
                for (ClassDefine clazz : classList) {
                    cache.put(clazz.getName(), clazz);
                    classIdMap.put(clazz.getId(), clazz);
                    // 查询类字段
                    sql = "SELECT * FROM sys_class_field WHERE class_id=?";
                    List<ClassField> fieldList = template.query(sql, ClassRowMapperFactory.getClassField(clazz), clazz.getId());
                    clazz.setFieldList(fieldList);
                    for (ClassField field : fieldList) {
                        classFieldIdMap.put(field.getId(), field);
                    }

                    // 查询类Form
                    sql = "SELECT * FROM sys_class_form WHERE class_id=?";
                    List<ClassForm> formList = template.query(sql, ClassRowMapperFactory.getClassForm(clazz), clazz.getId());
                    clazz.setFormList(formList);
                    // 查询类Form字段
                    sql = "SELECT * FROM sys_class_form_field WHERE form_id=?";
                    for (ClassForm form : formList) {
                        List<FormField> formFieldList = template.query(sql, ClassRowMapperFactory.getFormField(form), form.getId());
                        form.setFieldList(formFieldList);
                    }

                    // 查询类Table
                    sql = "SELECT * FROM sys_class_table WHERE class_id=?";
                    List<ClassTable> tableList = template.query(sql, ClassRowMapperFactory.getClassTable(clazz), clazz.getId());
                    clazz.setClassTableList(tableList);
                    // 查询类Table Query
                    sql = "SELECT * FROM sys_class_table_query WHERE table_id=?";

                }
            } else {
                classSortNum = 10;
                // 请空表sys_class_define
                template.clearTable("sys_class_define");
                for (DbmsSchema schema : DBManager.getDbms().getSchemaList()) {
                    for (DbmsTable table : schema.getTableList()) {
                        initClassDefFromTable(template, table);
                        classSortNum += 10;
                    }
                }
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            template.close();
        }
    }

    public static void initClassDefFromTable(JdbcTemplate template, DbmsTable table) throws Exception {
        // 将表定义信息插入到类定义信息中
        ClassDefine clazz = new ClassDefine();
        clazz.setName(tableNameToClassName(table.getName()));
        clazz.setCname(table.getDisplayName());
        clazz.setAuthor("system");
        clazz.setDesc(table.getComment());
        clazz.setVersion(table.getSchema().getVersion());
        clazz.setValid(true);
        clazz.setSortNum(classSortNum);
        // 插入类定义信息
        template.save(ClassPDBFactory.getClassDefine(clazz));

        List<ClassField> fieldList = new ArrayList<ClassField>();

        // 将表列信息插入到类字段信息中
        ClassField field;
        int fieldSortNum = 0;
        for (DbmsColumn column : table.getColumnList()) {
            field = new ClassField();
            field.setClassDef(clazz);
            field.setColumn(column);
            field.setName(columnNameToFieldName(column.getName()));
            field.setFieldDesc(column.getDisplayName());
            field.setType(column.getDataType());
            field.setValid(true);
            field.setSortNum(fieldSortNum += 10);
            // 插入表sys_class_field
            template.save(ClassPDBFactory.getClassField(field));
            fieldList.add(field);
        }
        clazz.setFieldList(fieldList);

        // 插入sys_class_table信息
        ClassTable classTable = new ClassTable();
        classTable.setName("default");
        classTable.setColWidth(80);
        classTable.setSortNum(classSortNum);
        classTable.setValid(true);
        classTable.setClassDefine(clazz);
        template.save(ClassPDBFactory.getClassTable(classTable));

        // 插入sys_class_table_field
        TableField tableField;
        fieldSortNum = 0;
        for (ClassField classField : fieldList) {
            tableField = new TableField();
            tableField.setClassTable(classTable);
            tableField.setClassField(classField);
            tableField.setDisplayName(classField.getFieldDesc());
            tableField.setColWidth(80);
            tableField.setValid(true);
            tableField.setDisplay(true);
            tableField.setSortNum(fieldSortNum += 10);
            // 插入表
            template.save(ClassPDBFactory.getTableField(tableField));
        }

        // 插入sys_class_form信息
        ClassForm classForm = new ClassForm();
        classForm.setName("default");
        classForm.setColCount(3);
        classForm.setColWidth(120);
        classForm.setClassDefine(clazz);
        classForm.setLabelGap(5);
        classForm.setFieldGap(15);
        classForm.setSortNum(classSortNum);
        classForm.setValid(true);
        template.save(ClassPDBFactory.getClassForm(classForm));

        // 插入sys_class_form_field
        FormField formField;
        fieldSortNum = 0;
        for (ClassField classField : fieldList) {
            formField = new FormField();
            formField.setClassForm(classForm);
            formField.setClassField(classField);
            formField.setDisplayName(classField.getFieldDesc());
            formField.setSingleLine(false);
            formField.setWidth(120);
            formField.setDisplay(true);
            formField.setSortNum(fieldSortNum += 10);
            formField.setValid(true);
            // 插入表
            template.save(ClassPDBFactory.getFormField(formField));
        }

        // 插入类-table关联表
        List<DbmsTable> tableList = new ArrayList<DbmsTable>();
        tableList.add(table);
        clazz.setDbmsTableList(tableList);
        List<ClassDefine> classList = new ArrayList<ClassDefine>();
        classList.add(clazz);
        table.setClassList(classList);
        template.save(ClassPDBFactory.getRClassTable(clazz.getId(), table.getId()));

        // 存入缓存
        cache.put(clazz.getName(), clazz);
        classIdMap.put(clazz.getId(), clazz);
    }

    /**
     * 装载类定义信息
     *
     * @param className 类名
     * @return 返回类定义信息
     */
    public static ClassDefine loadClassDef(String className) {
        return cache.get(className);
    }

    /**
     * 将数据库的表名，准换为类名，例如sys_dbms_define,转换后的结果是DbmsDefine
     *
     * @param tableName 数据库表名
     * @return 返回类名
     */
    private static String tableNameToClassName(String tableName) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (String str : tableName.split("_")) {
            if (i++ == 0) {
                continue;
            }

            result.append(StringUtil.firstCharToUpper(str.toLowerCase()));
        }

        return result.toString();
    }

    private static String columnNameToFieldName(String columnName) {
        StringBuilder result = new StringBuilder();
        for (String str : columnName.split("_")) {
            result.append(StringUtil.firstCharToUpper(str.toLowerCase()));
        }

        return result.toString();
    }
}
