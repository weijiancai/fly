package com.fly.sys.persist;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author weijiancai
 * @since 1.0.0
 */
public interface DAO<T> {
    /**
     * 持久化对象
     *
     * @param obj 要持久化的对象
     */
    void save(Object obj) throws Exception;

    /**
     * 更新对象
     *
     * @param obj 要更新的对象
     */
    void update(Object obj);

    /**
     * 查询所有的持久化对象
     *
     * @return 返回所有的持久化对象
     */
    <T> List<T> getAll();
}
