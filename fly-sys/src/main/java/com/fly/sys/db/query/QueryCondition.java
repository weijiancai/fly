package com.fly.sys.db.query;

import com.fly.sys.dict.QueryMode;
import com.fly.sys.util.UString;

import java.util.ArrayList;
import java.util.List;

import static com.fly.sys.dict.QueryMode.EQUAL;

/**
 * 查询条件
 *
 * @author weijiancai
 * @since 1.0.0
 */
public class QueryCondition {
    private StringBuilder conditions = new StringBuilder(" 1=1 ");
    private List<Object> valueList = new ArrayList<Object>();

    public void addCondition(String column, QueryMode queryMode, Object... values) {
        String operator = getOperator(queryMode);
        if (values != null) {
            if (values.length == 1) {
                Object value = values[0];
                if (value instanceof String && UString.isEmpty(value.toString())) {
                    return;
                }

                if (QueryMode.LIKE == queryMode) {
                    conditions.append(" AND ").append(column).append(operator).append("'%").append(value.toString()).append("%'");
                } else if (QueryMode.LEFT_LIKE == queryMode) {
                    conditions.append(" AND ").append(column).append(operator).append("'%").append(value.toString()).append("'");
                } else if (QueryMode.RIGHT_LIKE == queryMode) {
                    conditions.append(" AND ").append(column).append(operator).append("'").append(value.toString()).append("%'");
                } else {
                    conditions.append(" AND ").append(column).append(operator).append("?");
                    valueList.add(value);
                }
            } else if (values.length > 1) {
                if (QueryMode.BETWEEN == queryMode) {
                    conditions.append(" AND ").append(column).append(" BETWEEN ? AND ? ");
                    valueList.add(values[0]);
                    valueList.add(values[1]);
                }
            }

        }
    }

    private String getOperator(QueryMode queryMode) {
        switch (queryMode) {
            case EQUAL:
                return  "=";
            case NOT_EQUAL:
                return "<>";
            case LESS_THAN:
                return "<";
            case LEFT_EQUAL:
                return "<=";
            case GREATER_THAN:
                return ">";
            case GREATER_EQUAL:
                return ">=";
            case LIKE:
            case LEFT_LIKE:
            case RIGHT_LIKE:
                return " LIKE ";
            default:
                return "=";
        }
    }

    public String getConditions() {
        return conditions.toString();
    }

    public List<Object> getValueList() {
        return valueList;
    }
}
