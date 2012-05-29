package com.fly.sys.clazz;

import com.fly.sys.db.DBManager;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class Query {
    private ClassDefine classDefine;

    public Query(ClassDefine classDefine) {
        this.classDefine = classDefine;
    }

    public List<Map<String, Object>> list() {
        StringBuilder columnBuffer = new StringBuilder();
        String tableName = "";
        String columnStr = "";
        for (DbmsTable table : classDefine.getDbmsTableList()) {
            tableName = table.getName();
            for (DbmsColumn column : table.getColumnList()) {
                columnBuffer.append(column.getName()).append(" ").append(column.getName().replace("_", "")).append(",");
            }
            if (columnBuffer.toString().endsWith(",")) {
                columnStr = columnBuffer.substring(0, columnBuffer.length() - 1);
            } else {
                columnStr = columnBuffer.toString();
            }
        }

        String sql = String.format("SELECT %s FROM %s", columnStr, tableName);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        try {
            JdbcTemplate template = new JdbcTemplate(DBManager.getConn());
            result.addAll(template.queryForList(sql.toLowerCase()));
//            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
