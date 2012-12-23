package com.fly.base.pm.entity;

import com.fly.base.persist.Persistence;
import com.fly.base.persist.PersistenceFactory;
import com.fly.base.util.Callback;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class GroupTest {
    @Test
    public void testSaveGroup() throws Exception {
        Persistence<Group> persistence = PersistenceFactory.getJpaPersistence();
        persistence.persist(new Callback<EntityManager>() {
            @Override
            public void call(EntityManager entityManager, Object... obj) throws Exception {
                entityManager.persist(new Group("股份有限公司", entityManager.getReference(GroupType.class, 1)));
                entityManager.persist(new Group("华东区", entityManager.getReference(GroupType.class, 2)));
                entityManager.persist(new Group("华南区", entityManager.getReference(GroupType.class, 2)));
                entityManager.persist(new Group("上海分公司", entityManager.getReference(GroupType.class, 3)));
                entityManager.persist(new Group("徐家汇", entityManager.getReference(GroupType.class, 4)));
                entityManager.persist(new Group("浦东", entityManager.getReference(GroupType.class, 4)));
            }
        });
    }
}
