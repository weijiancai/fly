package com.fly.base.persist.jpa;


import com.fly.base.persist.DAO;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * DAO借口的JPA实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class JPADao<T> implements DAO<T> {

    public JPADao() {

    }

    @Override
    public void save(T obj) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();

    }

    @Override
    public void delete(T obj) throws Exception {
    }

    @Override
    public void update(T obj) throws Exception {
    }

    @Override
    public Set<T> getAll() {
        return null;
    }

    @Override
    public void persist() throws Exception {

    }
}
