package com.fly.base.persist.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA工具类
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class JPAUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("sys");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
