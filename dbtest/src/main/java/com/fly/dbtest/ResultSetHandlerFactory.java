package com.fly.dbtest;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetHandler 工厂
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class ResultSetHandlerFactory {
    /**
     * 获得单个Int类型结果处理器
     *
     * @return 返回单个Int类型处理器
     */
    public static ResultSetHandler<Integer> singleInt() {
        return new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return rs.getInt(1);
                }

                return -1;
            }
        };
    }

    /**
     * 获得单个String类型处理器
     *
     * @return 返回单个String类型处理器
     */
    public static ResultSetHandler<String> singleString() {
        return new ResultSetHandler<String>() {
            @Override
            public String handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return rs.getString(1);
                }

                return null;
            }
        };
    }
}
