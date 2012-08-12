package com.fly.sys.clazz;

import com.fly.sys.R;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weijiancai
 */
public class ClassRowMapperFactory {
    public static RowMapper<ClassDefine> getClassDefine() {
        return new RowMapper<ClassDefine>() {
            @Override
            public ClassDefine mapRow(ResultSet rs) throws SQLException {
                ClassDefine def = new ClassDefine();

                def.setId(rs.getString(R.classdef.id));
                def.setName(rs.getString("name"));
                def.setCname(rs.getString(R.classdef.cname));
                def.setAuthor(rs.getString(R.classdef.author));
                def.setVersion(rs.getString(R.classdef.version));
                def.setDesc(rs.getString(R.classdef.class_desc));
                def.setSuperClass(rs.getString(R.classdef.super_class));
                def.setValid("T".equals(rs.getString("is_valid")));
                def.setInputDate(rs.getDate("input_date"));
                def.setSortNum(rs.getInt("sort_num"));

                return def;
            }
        };
    }

    public static RowMapper<ClassField> getClassField(final ClassDefine clazz) {
        return new RowMapper<ClassField>() {
            @Override
            public ClassField mapRow(ResultSet rs) throws SQLException {
                ClassField field = new ClassField();

                field.setId(rs.getString("id"));
                field.setFieldDesc(rs.getString("field_desc"));
                field.setName(rs.getString("name"));
                field.setType(rs.getString("type"));
                field.setValid("T".equals(rs.getString("is_valid")));
                field.setInputDate(rs.getDate("input_date"));
                field.setSortNum(rs.getInt("sort_num"));
                field.setClassDef(clazz);
                field.setColumn(DBManager.getColumnById(rs.getString("col_id")));

                return field;
            }
        };
    }

    public static RowMapper<ClassForm> getClassForm(final ClassDefine clazz) {
        return new RowMapper<ClassForm>() {
            @Override
            public ClassForm mapRow(ResultSet rs) throws SQLException {
                ClassForm form = new ClassForm();

                form.setId(rs.getString("id"));
                form.setName(rs.getString("name"));
                form.setFormType(rs.getString("form_type"));
                form.setColCount(rs.getInt("col_Count"));
                form.setColWidth(rs.getInt("col_width"));
                form.setLabelGap(rs.getInt("label_gap"));
                form.setFieldGap(rs.getInt("field_gap"));
                form.setHgap(rs.getInt("hgap"));
                form.setVgap(rs.getInt("vgap"));
                form.setValid("T".equals(rs.getString("is_valid")));
                form.setInputDate(rs.getDate("input_date"));
                form.setSortNum(rs.getInt("sort_num"));
                form.setClassDefine(clazz);

                return form;
            }
        };
    }

    public static RowMapper<FormField> getFormField(final ClassForm form) {
        return new RowMapper<FormField>() {
            @Override
            public FormField mapRow(ResultSet rs) throws SQLException {
                FormField field = new FormField();

                field.setId(rs.getString("id"));
                field.setDisplayName(rs.getString("display_name"));
                field.setDisplayStyle(rs.getString("display_style"));
                field.setHeight(rs.getInt("height"));
                field.setWidth(rs.getInt("width"));
                field.setSingleLine("T".equals(rs.getString("is_single_line")));
                field.setDisplay("T".equals(rs.getString("is_display")));
                field.setValid("T".equals(rs.getString("is_valid")));
                field.setInputDate(rs.getDate("input_date"));
                field.setSortNum(rs.getInt("sort_num"));
                field.setClassForm(form);
                field.setClassField(ClassManager.getClassFieldById(rs.getString("field_id")));

                return field;
            }
        };
    }

    public static RowMapper<ClassQuery> getClassQuery(final ClassDefine clazz) {
        return new RowMapper<ClassQuery>() {
            @Override
            public ClassQuery mapRow(ResultSet rs) throws SQLException {
                ClassQuery query = new ClassQuery();

                query.setId(rs.getString("id"));
                query.setName(rs.getString("name"));
                query.setColCount(rs.getInt("col_Count"));
                query.setColWidth(rs.getInt("col_width"));
                query.setLabelGap(rs.getInt("label_gap"));
                query.setFieldGap(rs.getInt("field_gap"));
                query.setValid("T".equals(rs.getString("is_valid")));
                query.setInputDate(rs.getDate("input_date"));
                query.setSortNum(rs.getInt("sort_num"));
                query.setClassDefine(clazz);

                return query;
            }
        };
    }

    public static RowMapper<QueryField> getQueryField(final ClassQuery query) {
        return new RowMapper<QueryField>() {
            @Override
            public QueryField mapRow(ResultSet rs) throws SQLException {
                QueryField field = new QueryField();

                field.setId(rs.getString("id"));
                field.setDisplayName(rs.getString("display_name"));
                field.setDisplayStyle(rs.getString("display_style"));
                field.setHeight(rs.getInt("height"));
                field.setWidth(rs.getInt("width"));
                field.setOperator(rs.getString("operator"));
                field.setDisplay("T".equals(rs.getString("is_display")));
                field.setValid("T".equals(rs.getString("is_valid")));
                field.setInputDate(rs.getDate("input_date"));
                field.setSortNum(rs.getInt("sort_num"));
                field.setClassQuery(query);
                field.setClassField(ClassManager.getClassFieldById(rs.getString("field_id")));

                return field;
            }
        };
    }

    public static RowMapper<ClassTable> getClassTable(final ClassDefine clazz) {
        return new RowMapper<ClassTable>() {
            @Override
            public ClassTable mapRow(ResultSet rs) throws SQLException {
                ClassTable table = new ClassTable();

                table.setId(rs.getString("id"));
                table.setName(rs.getString("name"));
                table.setColWidth(rs.getInt("col_width"));
                table.setSql(rs.getString("sql_text"));
                table.setValid("T".equals(rs.getString("is_valid")));
                table.setInputDate(rs.getDate("input_date"));
                table.setSortNum(rs.getInt("sort_num"));
                table.setClassDefine(clazz);

                return table;
            }
        };
    }

    public static RowMapper<TableField> getClassTableField(final ClassTable table) {
        return new RowMapper<TableField>() {
            @Override
            public TableField mapRow(ResultSet rs) throws SQLException {
                TableField field = new TableField();

                field.setId(rs.getString("id"));
                field.setDisplayName(rs.getString("display_name"));
                field.setDisplayStyle(rs.getString("display_style"));
                field.setAlign(rs.getString("align"));
                field.setColWidth(rs.getInt("col_width"));
                field.setDisplay("T".equals(rs.getString("is_display")));
                field.setValid("T".equals(rs.getString("is_valid")));
                field.setInputDate(rs.getDate("input_date"));
                field.setSortNum(rs.getInt("sort_num"));
                field.setClassTable(table);

                field.setClassField(ClassManager.getClassFieldById(rs.getString("field_id")));

                return field;
            }
        };
    }
}
