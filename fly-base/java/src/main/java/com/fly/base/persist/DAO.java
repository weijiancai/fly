package com.fly.base.persist;

import java.util.Set;

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
    void save(T obj) throws Exception;

    /**
     * 删除对象
     *
     * @param obj 要删除的对象
     * @throws Exception
     */
    void delete(T obj) throws Exception;

    /**
     * 更新对象
     *
     * @param obj 要更新的对象
     */
    void update(T obj) throws Exception;

    /**
     * 查询所有的持久化对象
     *
     * @return 返回所有的持久化对象
     */
    Set<T> getAll();

    /**
     * 进行持久化操作
     *
     * @throws Exception
     */
    void persist() throws Exception;
}
