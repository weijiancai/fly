package com.fly.dbtest;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结果集处理器
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class RSHandler {
    private List<Object[]> objectArrayList;
    private List<String> colNameList;
    private List<Map<String, Object>> mapDataList;

    public RSHandler(ResultSet rs) {
        objectArrayList = new ArrayList<Object[]>();
        mapDataList = new ArrayList<Map<String, Object>>();
        colNameList = new ArrayList<String>();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();

            // 读取列名
            for (int i = 1; i <= count; i++) {
                colNameList.add(meta.getColumnName(i).toLowerCase());
            }

            Object[] objects;
            Map<String, Object> map;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                objects = new Object[count];

                for (int i = 0; i < count; i++) {
                    objects[i] = rs.getObject(i + 1);
                    map.put(meta.getColumnName(i + 1).toLowerCase(), objects[i]);
                }

                objectArrayList.add(objects);
                mapDataList.add(map);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(int row, int col, Object value) {
        if (row < 1 || col < 1 || row > objectArrayList.size() || objectArrayList.size() == 0 || col > objectArrayList.get(0).length) {
            return false;
        }

        return objectArrayList.get(row - 1)[col - 1].equals(value);
    }

    public boolean contains(int col, Object value) {
        if (col < 1 || objectArrayList.size() == 0 || col > objectArrayList.get(0).length) {
            return false;
        }

        for (Object[] objects : objectArrayList) {
            if (value instanceof String) {
                if (objects[col - 1].toString().equalsIgnoreCase(value.toString())) {
                    return true;
                }
            } else {
                if (objects[col - 1].equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean contains(Object rowObj) {
        boolean flag;
        String methodName;
        Object value;
        for (Map<String, Object> map : mapDataList) {
            flag = true;
            for (Method method : rowObj.getClass().getMethods()) {
                if (!method.getName().startsWith("get") || "getClass".equals(method.getName())) {
                    continue;
                }

                if (method.getName().startsWith("get")) {
                    methodName = method.getName().substring(3).toLowerCase();
                } else {
                    methodName = method.getName().toLowerCase();
                }

                if (!colNameList.contains(methodName)) {
                    throw new RuntimeException(String.format("列名【%s】不存在！", method.getName()));
                }

                try {
                    value = method.invoke(rowObj);
                    if (!value.equals(map.get(methodName))) {
                        flag = false;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (flag) {
                return true;
            }
        }
        return false;
    }
}
