package com.fly.sys.clazz;

import com.fly.sys.IPDB;
import com.fly.sys.util.UUIDUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ClassPDBFactory {
    public static IPDB getClassDefine(final ClassDefine classDefine) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                classDefine.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", classDefine.getId());
                map.put("name", classDefine.getName());
                map.put("cname", classDefine.getCname());
                map.put("super_class", classDefine.getSuperClass());
                map.put("author", classDefine.getAuthor());
                map.put("class_desc", classDefine.getDesc());
                map.put("version", classDefine.getVersion());
                map.put("is_valid", classDefine.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", classDefine.getSortNum());

                result.put("sys_class_define", map);

                return result;
            }
        };
    }

    public static IPDB getClassField(final ClassField classField) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                classField.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", classField.getId());
                map.put("name", classField.getName());
                map.put("col_id", classField.getColumn().getId());
                map.put("class_id", classField.getClassDef().getId());
                map.put("dz_category_id", classField.getDzCategoryId());
                map.put("field_desc", classField.getFieldDesc());
                map.put("type", classField.getType());
                map.put("is_valid", classField.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", classField.getSortNum());

                result.put("sys_class_field", map);

                return result;
            }
        };
    }

    public static IPDB getRClassTable(final String classId, final String tableId) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("class_id", classId);
                map.put("dbms_table_id", tableId);

                result.put("sys_r_class_table", map);

                return result;
            }
        };
    }

    public static IPDB getClassTable(final ClassTable table) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                table.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", table.getId());
                map.put("class_id", table.getClassDefine().getId());
                map.put("name", table.getName());
                map.put("col_width", table.getColWidth());
                map.put("is_valid", table.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", table.getSortNum());

                result.put("sys_class_table", map);

                return result;
            }
        };
    }

    public static IPDB getTableField(final TableField field) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                field.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", field.getId());
                map.put("class_table_id", field.getClassTable().getId());
                map.put("field_id", field.getClassField().getId());
                map.put("display_name", field.getDisplayName());
                map.put("display_style", field.getDisplayStyle());
                map.put("align", field.getAlign());
                map.put("is_display", field.isDisplay() ? "T" : "F");
                map.put("col_width", field.getColWidth());
                map.put("is_valid", field.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", field.getSortNum());

                result.put("sys_class_table_field", map);

                return result;
            }
        };
    }

    public static IPDB getClassForm(final ClassForm form) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                form.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", form.getId());
                map.put("class_id", form.getClassDefine().getId());
                map.put("name", form.getName());
                map.put("form_type", form.getFormType());
                map.put("col_width", form.getColWidth());
                map.put("col_count", form.getColCount());
                map.put("label_gap", form.getLabelGap());
                map.put("hgap", form.getHgap());
                map.put("vgap", form.getVgap());
                map.put("field_gap", form.getFieldGap());
                map.put("is_valid", form.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", form.getSortNum());

                result.put("sys_class_form", map);

                return result;
            }
        };
    }

    public static IPDB getFormField(final FormField field) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                field.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", field.getId());
                map.put("form_id", field.getClassForm().getId());
                map.put("field_id", field.getClassField().getId());
                map.put("display_name", field.getDisplayName());
                if (field.getDisplayStyle() != null) {
                    map.put("display_style", field.getDisplayStyle().ordinal());
                }
                map.put("is_display", field.isDisplay() ? "T" : "F");
                map.put("width", field.getWidth());
                map.put("height", field.getHeight());
                map.put("is_single_line", field.isSingleLine() ? "T" : "F");
                map.put("is_valid", field.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", field.getSortNum());

                result.put("sys_class_form_field", map);

                return result;
            }
        };
    }

    public static IPDB getFormFieldAppend(final FormField field) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("form_field_id", field.getId());
                map.put("query_mode", field.getQueryMode().ordinal());
                map.put("is_readonly", field.isReadonly() ? "T" : "F");
                map.put("is_required", field.isRequired() ? "T" : "F");
                map.put("data_type", field.getDataType());

                result.put("sys_form_field_append", map);

                return result;
            }
        };
    }

    public static IPDB getClassQuery(final ClassQuery classQuery) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                classQuery.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", classQuery.getId());
                map.put("class_id", classQuery.getClassDefine().getId());
                map.put("name", classQuery.getName());
                map.put("col_width", classQuery.getColWidth());
                map.put("col_count", classQuery.getColCount());
                map.put("label_gap", classQuery.getLabelGap());
                map.put("field_gap", classQuery.getFieldGap());
                map.put("is_valid", classQuery.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", classQuery.getSortNum());

                result.put("sys_class_query", map);

                return result;
            }
        };
    }

    public static IPDB getQueryField(final QueryField field) {
        return new IPDB() {
            @Override
            public Map<String, Map<String, Object>> getPDBMap() {
                field.setId(UUIDUtil.getUUID());

                Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", field.getId());
                map.put("query_id", field.getClassQuery().getId());
                map.put("field_id", field.getClassField().getId());
                map.put("display_name", field.getDisplayName());
                map.put("display_style", field.getDisplayStyle());
                map.put("is_display", field.isDisplay() ? "T" : "F");
                map.put("width", field.getWidth());
                map.put("height", field.getHeight());
                map.put("operator", field.getOperator());
                map.put("is_valid", field.isValid() ? "T" : "F");
                map.put("input_date", new Date());
                map.put("sort_num", field.getSortNum());

                result.put("sys_class_query_field", map);

                return result;
            }
        };
    }
}
