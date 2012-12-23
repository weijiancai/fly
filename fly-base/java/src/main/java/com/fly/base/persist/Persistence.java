package com.fly.base.persist;

import com.fly.base.util.Callback;

import javax.persistence.EntityManager;

/**
 * 持久化接口
 *
 * @author weijiancai
 * @version 0.0.1
 */
public interface Persistence<T> {
    void persist(DAO... daoList) throws Exception;

    /**
     * 持久化操作
     *
     * @param callback 回调函数
     * @throws Exception
     */
    void persist(Callback<EntityManager> callback) throws Exception;

    /**
     * 持久化对象
     *
     * @param objList 要持久化的对象列表
     */
    void save(T... objList) throws Exception;

    /**
     * 删除对象
     *
     * @param objList 要删除的对象列表
     * @throws Exception
     */
    void delete(T objList) throws Exception;

    /**
     * 更新对象
     *
     * @param objList 要更新的对象列表
     */
    void update(T objList) throws Exception;

    /**
     * 清空表
     *
     * @param classList 实体类型
     * @throws Exception
     */
    void clear(Class<?>... classList) throws Exception;
}
