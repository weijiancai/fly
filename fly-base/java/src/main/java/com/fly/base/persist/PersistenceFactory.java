package com.fly.base.persist;

import com.fly.base.persist.jpa.JPAUtil;
import com.fly.base.persist.jpa.JpaPersistence;

/**
 * 持久化工厂
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class PersistenceFactory {
    public static <T> Persistence<T> getJpaPersistence() {
        return new JpaPersistence<T>(JPAUtil.getEntityManager());
    }
}
