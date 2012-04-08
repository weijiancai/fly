package com.fly.sys.db;

import com.fly.sys.IPDB;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsDefine;
import com.fly.sys.db.meta.DbmsSchema;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.util.UUIDUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class DbmsPDBFactory {
    public static IPDB getDbmsDefine(final DbmsDefine dbmsDefine) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                dbmsDefine.setId(UUIDUtil.getUUID());
                dbmsDefine.setInputDate(new Date());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", dbmsDefine.getId());
                map.put("name", dbmsDefine.getName());
                map.put("type", dbmsDefine.getType());
                map.put("host", dbmsDefine.getHost());
                map.put("port", dbmsDefine.getPort());
                map.put("driver_class", dbmsDefine.getDriverClass());
                map.put("is_valid", dbmsDefine.isValid() ? "T" : "F");
                map.put("input_date", dbmsDefine.getInputDate());
                map.put("sort_num", dbmsDefine.getSortNum());

                result.put("sys_dbms_define", map);

                return result;
            }
        };
    }

    public static IPDB getDbmsSchema(final DbmsSchema schema) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                schema.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", schema.getId());
                map.put("dbms_id", schema.getDbms().getId());
                map.put("name", schema.getName());
                map.put("alias", schema.getAlias());
                map.put("version", schema.getVersion());
                map.put("url", schema.getUrl());
                map.put("is_default", schema.isDefault() ? "T" : "F");
                map.put("user_name", schema.getUserName());
                map.put("password", schema.getPassword());
                map.put("is_valid", schema.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", schema.getSortNum());

                result.put("sys_dbms_schema", map);

                return result;
            }
        };
    }

    public static IPDB getDbmsTable(final DbmsTable table) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                table.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", table.getId());
                map.put("schema_id", table.getSchema().getId());
                map.put("name", table.getName());
                map.put("alias", table.getAlias());
                map.put("comment", table.getComment());
                map.put("display_name", table.getDisplayName());
                map.put("is_valid", table.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", table.getSortNum());

                result.put("sys_dbms_table", map);

                return result;
            }
        };
    }

    public static IPDB getDbmsColumn(final DbmsColumn column) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                column.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", column.getId());
                map.put("table_id", column.getTable().getId());
                map.put("name", column.getName());
                map.put("alias", column.getAlias());
                map.put("comment", column.getComment());
                map.put("display_name", column.getDisplayName());
                map.put("is_valid", column.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", column.getSortNum());
                map.put("data_type", column.getDataType());
                if (column.getCode() != null) {
                    map.put("code_id", column.getCode().getId());
                }
                map.put("default_value", column.getDefaultValue());
                map.put("is_nullable", column.isNullable() ? "T" : "F");
                map.put("is_pk", column.isPk() ? "T" : "F");
                map.put("is_fk", column.isFk() ? "T" : "F");
                if (column.getFkColumn() != null) {
                    map.put("fk_column_id", column.getFkColumn().getId());
                }
                map.put("max_length", column.getMaxLength());

                result.put("sys_dbms_column", map);

                return result;
            }
        };
    }
}
