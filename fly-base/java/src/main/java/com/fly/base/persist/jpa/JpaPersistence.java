package com.fly.base.persist.jpa;

import com.fly.base.persist.DAO;
import com.fly.base.persist.Persistence;
import com.fly.base.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import java.lang.annotation.Annotation;

/**
 * JPA持久化接口实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class JpaPersistence<T> implements Persistence<T> {
    private EntityManager entityManager;

    public JpaPersistence(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(DAO... daoList) throws Exception {
        try {
            entityManager.getTransaction().begin();
            for (DAO dao : daoList) {
                dao.persist();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void persist(Callback<EntityManager> callback) throws Exception {
        try {
            entityManager.getTransaction().begin();

            callback.call(entityManager);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
//            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void clear(Class<?>... classList) throws Exception {
        try {
            entityManager.getTransaction().begin();
            Query query;
            for (Class clazz : classList) {
                query = entityManager.createQuery("DELETE FROM " + clazz.getSimpleName());
                query.executeUpdate();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
//            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Object... objList) throws Exception {
        try {
            entityManager.getTransaction().begin();
            for (Object obj : objList) {
                entityManager.persist(obj);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
//            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Object objList) throws Exception {
    }

    @Override
    public void update(Object objList) throws Exception {
    }
}