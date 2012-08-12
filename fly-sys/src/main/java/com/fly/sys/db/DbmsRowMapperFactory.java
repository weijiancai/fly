package com.fly.sys.db;

import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsDefine;
import com.fly.sys.db.meta.DbmsSchema;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.dict.CodeManager;
import com.fly.sys.dict.DictCategory;
import com.fly.sys.dict.DictCode;
import com.fly.sys.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weijiancai
 */
public class DbmsRowMapperFactory {
    public static RowMapper<DbmsDefine> getDbmsDefine() {
        return new RowMapper<DbmsDefine>() {
            @Override
            public DbmsDefine mapRow(ResultSet rs) throws SQLException {
                DbmsDefine dbmsDefine = new DbmsDefine();
                dbmsDefine.setId(rs.getString("id"));
                dbmsDefine.setName(rs.getString("name"));
                dbmsDefine.setType(rs.getString("type"));
                dbmsDefine.setDriverClass(rs.getString("driver_class"));
                dbmsDefine.setValid("T".equals(rs.getString("is_valid")));
                dbmsDefine.setSortNum(rs.getInt("sort_num"));
                dbmsDefine.setInputDate(rs.getDate("input_date"));
                dbmsDefine.setHost(rs.getString("host"));
                dbmsDefine.setPort(rs.getString("port"));

                return dbmsDefine;
            }
        };
    }

    public static RowMapper<DbmsSchema> getDbmsSchema(final DbmsDefine dbmsDefine) {
        return new RowMapper<DbmsSchema>() {
            @Override
            public DbmsSchema mapRow(ResultSet rs) throws SQLException {
                DbmsSchema schema = new DbmsSchema();
                schema.setDbms(dbmsDefine);
                schema.setId(rs.getString("id"));
                schema.setName(rs.getString("name"));
                schema.setAlias(rs.getString("alias"));
                schema.setVersion(rs.getString("version"));
                schema.setValid("T".equals(rs.getString("is_valid")));
                schema.setSortNum(rs.getInt("sort_num"));
                schema.setInputDate(rs.getDate("input_date"));
                schema.setUrl(rs.getString("url"));
                schema.setUserName(rs.getString("user_name"));
                schema.setDefault("T".equals(rs.getString("is_default")));
                schema.setPassword(rs.getString("password"));

                return schema;
            }
        };
    }

    public static RowMapper<DbmsTable> getDbmsTable(final DbmsSchema schema) {
        return new RowMapper<DbmsTable>() {
            @Override
            public DbmsTable mapRow(ResultSet rs) throws SQLException {
                DbmsTable table = new DbmsTable();
                table.setSchema(schema);
                table.setId(rs.getString("id"));
                table.setName(rs.getString("name"));
                table.setAlias(rs.getString("alias"));
                table.setComment(rs.getString("comment"));
                table.setDisplayName(rs.getString("display_name"));
                table.setValid("T".equals(rs.getString("is_valid")));
                table.setSortNum(rs.getInt("sort_num"));
                table.setInputDate(rs.getDate("input_date"));

                return table;
            }
        };
    }

    public static RowMapper<DbmsColumn> getDbmsColumn(final DbmsTable table) {
        return new RowMapper<DbmsColumn>() {
            @Override
            public DbmsColumn mapRow(ResultSet rs) throws SQLException {
                DbmsColumn column = new DbmsColumn();
                column.setTable(table);
                column.setId(rs.getString("id"));
                column.setName(rs.getString("name"));
                column.setAlias(rs.getString("alias"));
                column.setComment(rs.getString("comment"));
                column.setDisplayName(rs.getString("display_name"));
                column.setDataType(rs.getString("data_type"));
                column.setDefaultValue(rs.getString("default_value"));
                column.setValid("T".equals(rs.getString("is_valid")));
                column.setSortNum(rs.getInt("sort_num"));
                column.setInputDate(rs.getDate("input_date"));
                column.setFkColumnId(rs.getString("fk_column_id"));
                column.setFk("T".equals(rs.getString("is_fk")));
                column.setPk("T".equals(rs.getString("is_pk")));
                String codeId = rs.getString("code_id");
                if (StringUtil.isNotEmpty(codeId)) {
                    column.setCode(CodeManager.getCodeById(codeId));
                }

                return column;
            }
        };
    }

    public static RowMapper<DictCode> getCode(final DictCategory category) {
        return new RowMapper<DictCode>() {
            @Override
            public DictCode mapRow(ResultSet rs) throws SQLException {
                DictCode code = new DictCode();
                code.setCategory(category);
                code.setId(rs.getString("id"));
                code.setName(rs.getString("name"));
                code.setValue(rs.getString("value"));
                code.setValid("T".equals(rs.getString("is_valid")));
                code.setInputDate(rs.getDate("input_date"));
                code.setSortNum(rs.getInt("sort_num"));

                return code;
            }
        };
    }

    public static RowMapper<DictCategory> getCategory() {
        return new RowMapper<DictCategory>() {
            @Override
            public DictCategory mapRow(ResultSet rs) throws SQLException {
                DictCategory category = new DictCategory();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setValid("T".equals(rs.getString("is_valid")));
                category.setInputDate(rs.getDate("input_date"));
                category.setSortNum(rs.getInt("sort_num"));

                return category;
            }
        };
    }
}
