package com.fly.sys.clazz;

import com.alibaba.fastjson.JSONObject;
import com.fly.sys.db.DBManager;
import com.fly.sys.db.JdbcTemplate;
import com.fly.sys.db.QueryResult;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.util.UUIDUtil;

import java.util.ArrayList;
import java.util.HashMap;
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

    public QueryResult<Map<String, Object>> list(String conditions, List values) {
        return list(conditions, values, 1, 20);
    }

    public QueryResult<Map<String, Object>> list(String conditions, List values, int page, int rows) {
        String sql = classDefine.getQuerySql();
        QueryResult<Map<String, Object>> queryResult = new QueryResult<Map<String, Object>>();

        try {
            JdbcTemplate template = new JdbcTemplate(DBManager.getSysConn());
            queryResult = template.queryForList(sql.toLowerCase(), conditions, values, page, rows);
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return queryResult;
    }

    public void update(Map<String, Object> valueMap, Map<String, Object> conditionMap, String tableName) {
        JdbcTemplate template = null;
        try {
            if (valueMap == null || valueMap.size() == 0) {
                return;
            }
            template = new JdbcTemplate(DBManager.getSysConn());
            template.update(valueMap, conditionMap, tableName);
            template.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != template) {
                template.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void update(Map<String, Object> data, String method) throws Exception {
        if (data != null) {
            JdbcTemplate template = null;
            try {
                template = new JdbcTemplate(DBManager.getSysConn());
                for (String key : data.keySet()) {
                    Object obj = data.get(key);
                    Map<String, Object> valueMap = null;
                    if (obj instanceof Map) {
                        valueMap = (Map<String, Object>) obj;
                    }

                    Map<String, Object> map = new HashMap<String, Object>();
                    DbmsTable table = classDefine.getDbTableByName(key);
                    if (table != null) {
                        List<DbmsColumn> columnList = table.getPkColumn();
                        for (DbmsColumn column : columnList) {
                            map.put(column.getTable().getName() + "." + column.getName(), UUIDUtil.getUUID());
                        }

                        if (map.size() > 0) {
                            if ("save".equals(method) && valueMap != null && valueMap.size() > 0) {
                                valueMap.putAll(map);
                                template.save(valueMap, table.getName());
                            } else if("update".equals(method) && valueMap != null && valueMap.size() > 0) {
                                Map<String, Object> conditionMap = new HashMap<String, Object>();
                                for (String pk : map.keySet()) {
                                    if (valueMap.containsKey(pk)) {
                                        conditionMap.put(pk, valueMap.get(pk));
                                        valueMap.remove(pk);
                                    }
                                }
                                if (conditionMap.size() > 0) {
                                    template.update(valueMap, conditionMap, key);
                                }
                            } else if ("delete".equals(method)) {
                                Map<String, Object> conditionMap = new HashMap<String, Object>();
                                for (String pk : map.keySet()) {
                                    if (valueMap.containsKey(pk)) {
                                        conditionMap.put(pk, valueMap.get(pk));
                                        valueMap.remove(pk);
                                    }
                                }

                                if (conditionMap.size() > 0) {
                                    template.delete(conditionMap, key);
                                }
                            }
                        }

                    }
                }

                template.commit();

            } finally {
                if (null != template) {
                    template.close();
                }
            }
        }
    }

    public void delete(Map<String, Object> map) {

    }
}
