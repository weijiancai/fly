package com.fly.sys.db.query;

import com.fly.sys.util.UString;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件
 *
 * @author weijiancai
 * @since 1.0.0
 */
public class QueryCondition {
    private StringBuilder conditions = new StringBuilder(" 1=1 ");
    private List<Object> values = new ArrayList<Object>();

    public void addCondition(String column, String operator, Object value) {
        if (value != null) {
            if (value instanceof String) {
                if (UString.isNotEmpty(value.toString())) {
                    conditions.append(" AND ").append(column).append(operator).append("?");
                    values.add(value);
                }
            } else {
                conditions.append(" AND ").append(column).append(operator).append("?");
                values.add(value);
            }
        }
    }

    public StringBuilder getConditions() {
        return conditions;
    }

    public List<Object> getValues() {
        return values;
    }
}
