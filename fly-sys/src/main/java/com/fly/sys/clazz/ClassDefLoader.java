package com.fly.sys.clazz;

import com.fly.sys.config.SysInfo;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsDefine;
import com.fly.sys.db.meta.DbmsSchema;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.util.UString;

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

    /*static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void init() throws Exception {
        Connection conn = DBManager.getSysConn();
        JdbcTemplate template = new JdbcTemplate(conn);
        try {
            if (SysInfo.isClassDefInit()) { // ClassDef 已经初始化
                String sql = "SELECT * FROM sys_class_define";
                List<ClassDefine> classList = template.query(sql, ClassRowMapperFactory.getClassDefine());
                for (ClassDefine clazz : classList) {
                    cache.put(clazz.getName().toLowerCase(), clazz);
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

                    // 查询类Query
                    sql = "SELECT * FROM sys_class_query WHERE class_id=?";
                    List<ClassQuery> queryList = template.query(sql, ClassRowMapperFactory.getClassQuery(clazz), clazz.getId());
                    clazz.setClassQueryList(queryList);
                    // 查询类Form字段
                    sql = "SELECT * FROM sys_class_query_field WHERE query_id=?";
                    for (ClassQuery query : queryList) {
                        List<QueryField> queryFieldList = template.query(sql, ClassRowMapperFactory.getQueryField(query), query.getId());
                        query.setQueryFieldList(queryFieldList);
                    }

                    // 查询类Table
                    sql = "SELECT * FROM sys_class_table WHERE class_id=?";
                    List<ClassTable> tableList = template.query(sql, ClassRowMapperFactory.getClassTable(clazz), clazz.getId());
                    clazz.setClassTableList(tableList);
                    // 查询类Table字段
                    sql = "SELECT * FROM sys_class_table_field WHERE class_table_id=?";
                    for (ClassTable table : tableList) {
                        List<TableField> tableFieldList = template.query(sql, ClassRowMapperFactory.getClassTableField(table), table.getId());
                        table.setTableFieldList(tableFieldList);
                    }

                    // 查询类Table Query
                    sql = "SELECT * FROM sys_class_table_query WHERE table_id=?";
                }
            } else {
                classSortNum = 10;
                // 请空表sys_class_define
                template.clearTable("sys_class_define");
                for (DbmsDefine dbms : DBManager.getDbmsList()) {
                    for (DbmsSchema schema : dbms.getSchemaList()) {
                        for (DbmsTable table : schema.getTableList()) {
                            initClassDefFromTable(template, table);
                            classSortNum += 10;
                        }
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

        // 初始化明细
        initItem();
    }

    private static void initItem() {
        DbmsColumn column;
        for (ClassField field : classFieldIdMap.values()) {
            column = field.getColumn();
            if (null != column && column.isFk()) {
                field.getClassDef().addItemClassName(UString.tableNameToClassName(column.getTable().getName()));
            }
        }
    }

    public static void initClassDefFromTable(JdbcTemplate template, DbmsTable table) throws Exception {
        // 将表定义信息插入到类定义信息中
        ClassDefine clazz = new ClassDefine();
        clazz.setName(UString.tableNameToClassName(table.getName()));
        clazz.setCname(table.getDisplayName());
        clazz.setAuthor("system");
        clazz.setDesc(table.getComment());
        clazz.setVersion(table.getSchema().getVersion());
        clazz.setValid(true);
        clazz.setSortNum(classSortNum);
        // 插入类定义信息
        template.save(ClassPDBFactory.getClassDefine(clazz));
        classIdMap.put(clazz.getId(), clazz);

        List<ClassField> fieldList = new ArrayList<ClassField>();

        // 将表列信息插入到类字段信息中
        ClassField field;
        int fieldSortNum = 0;
        for (DbmsColumn column : table.getColumnList()) {
            field = new ClassField();
            field.setClassDef(clazz);
            field.setColumn(column);
            field.setName(UString.columnNameToFieldName(column.getName()));
            field.setFieldDesc(column.getDisplayName());
            field.setType(column.getDataType());
            field.setValid(true);
            field.setSortNum(fieldSortNum += 10);
            // 插入表sys_class_field
            template.save(ClassPDBFactory.getClassField(field));
            fieldList.add(field);
            classFieldIdMap.put(field.getId(), field);
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
        List<ClassTable> classTableList = new ArrayList<ClassTable>();
        classTableList.add(classTable);
        clazz.setClassTableList(classTableList);

        // 插入sys_class_table_field
        List<TableField> tableFieldList = new ArrayList<TableField>();
        TableField tableField;
        fieldSortNum = 0;
        for (ClassField classField : fieldList) {
            tableField = new TableField();
            tableField.setClassTable(classTable);
            tableField.setClassField(classField);
            tableField.setDisplayName(classField.getFieldDesc());
            tableField.setColWidth(getColWidth(classField));
            tableField.setValid(true);
            tableField.setDisplay(true);
            tableField.setSortNum(fieldSortNum += 10);
            // 插入表
            template.save(ClassPDBFactory.getTableField(tableField));
            tableFieldList.add(tableField);
        }
        classTable.setTableFieldList(tableFieldList);

        // 插入sys_class_form信息
        initClassForm(template, clazz, fieldList, "0");
        initClassForm(template, clazz, fieldList, "1");

        // 插入sys_class_query信息
        ClassQuery classQuery = new ClassQuery();
        classQuery.setName("default");
        classQuery.setColCount(3);
        classQuery.setColWidth(180);
        classQuery.setClassDefine(clazz);
        classQuery.setLabelGap(5);
        classQuery.setFieldGap(15);
        classQuery.setSortNum(classSortNum);
        classQuery.setValid(true);
        template.save(ClassPDBFactory.getClassQuery(classQuery));
        List<ClassQuery> classQueryList = new ArrayList<ClassQuery>();
        classQueryList.add(classQuery);
        clazz.setClassQueryList(classQueryList);

        // 插入sys_class_query_field
        List<QueryField> queryFieldList = new ArrayList<QueryField>();
        QueryField queryField;
        fieldSortNum = 0;
        for (ClassField classField : fieldList) {
            queryField = new QueryField();
            queryField.setClassQuery(classQuery);
            queryField.setClassField(classField);
            queryField.setDisplayName(classField.getFieldDesc());
            queryField.setOperator("=");
            queryField.setWidth(180);
            queryField.setDisplay(true);
            queryField.setSortNum(fieldSortNum += 10);
            queryField.setValid(true);
            // 插入表
            template.save(ClassPDBFactory.getQueryField(queryField));
            queryFieldList.add(queryField);
        }
        classQuery.setQueryFieldList(queryFieldList);

        // 插入类-table关联表
        List<DbmsTable> tableList = new ArrayList<DbmsTable>();
        tableList.add(table);
        clazz.setDbmsTableList(tableList);
        List<ClassDefine> classList = new ArrayList<ClassDefine>();
        classList.add(clazz);
        table.setClassList(classList);
        template.save(ClassPDBFactory.getRClassTable(clazz.getId(), table.getId()));

        // 存入缓存
        cache.put(clazz.getName().toLowerCase(), clazz);
        classIdMap.put(clazz.getId(), clazz);
    }

    private static void initClassForm(JdbcTemplate template, ClassDefine clazz, List<ClassField> fieldList, String formType) throws Exception {
        int fieldSortNum;
        ClassForm classForm = new ClassForm();
        classForm.setFormType(formType);
        if ("0".equals(formType)) {
            classForm.setName(clazz.getName() + "_Query");
        } else if ("1".equals(formType)) {
            classForm.setName(clazz.getName() + "_Edit");
        }
        classForm.setColCount(3);
        classForm.setColWidth(180);
        classForm.setClassDefine(clazz);
        classForm.setLabelGap(5);
        classForm.setFieldGap(15);
        classForm.setHgap(3);
        classForm.setVgap(5);
        classForm.setSortNum(classSortNum);
        classForm.setValid(true);
        template.save(ClassPDBFactory.getClassForm(classForm));
        List<ClassForm> classFormList = new ArrayList<ClassForm>();
        classFormList.add(classForm);
        clazz.setFormList(classFormList);

        // 插入sys_class_form_field
        List<FormField> formFieldList = new ArrayList<FormField>();
        FormField formField;
        fieldSortNum = 0;
        for (ClassField classField : fieldList) {
            formField = new FormField();
            formField.setClassForm(classForm);
            formField.setClassField(classField);
            formField.setDisplayName(classField.getFieldDesc());
            formField.setSingleLine(false);
            formField.setWidth(180);
            formField.setDisplay(true);
            formField.setSortNum(fieldSortNum += 10);
            formField.setValid(true);
            // 插入表
            template.save(ClassPDBFactory.getFormField(formField));
            formFieldList.add(formField);
        }
        classForm.setFieldList(formFieldList);
    }

    private static int getColWidth(ClassField classField) {
        int max = classField.getColumn().getMaxLength();
        if (max == 32) {
            return 80;
        } else if (max == 64) {
            return 160;
        } else if (max == 128) {
            return 220;
        } else if (max == 1024) {
            return 250;
        }

        return 60;
    }

    /**
     * 装载类定义信息
     *
     * @param className 类名
     * @return 返回类定义信息
     */
    public static ClassDefine loadClassDef(String className) {
        return cache.get(className.toLowerCase());
    }
}
