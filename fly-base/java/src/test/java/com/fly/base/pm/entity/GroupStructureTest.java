package com.fly.base.pm.entity;

import com.fly.base.persist.Persistence;
import com.fly.base.persist.PersistenceFactory;
import com.fly.base.persist.jpa.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class GroupStructureTest {
    @Test
    public void testSaveGroupStructure() throws Exception {
        Persistence<GroupStructure> persistence = PersistenceFactory.getJpaPersistence();
        persistence.save(
                new GroupStructure(new GroupStructurePK(2, 2, 1)),
                new GroupStructure(new GroupStructurePK(2, 3, 1)),
                new GroupStructure(new GroupStructurePK(2, 4, 2)),
                new GroupStructure(new GroupStructurePK(2, 5, 4)),
                new GroupStructure(new GroupStructurePK(2, 6, 4))
        );
    }

    @Test
    public void queryChildren() throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        // 找出父组织4
        GroupStructureType type = em.find(GroupStructureType.class, 2);
        Group group = em.find(Group.class, 4l);
        Group parentGroup = em.find(Group.class, 2l);
//        GroupStructure parent = em.find(GroupStructure.class, new GroupStructurePK(type, group, parentGroup));
        GroupStructure parent = em.find(GroupStructure.class, new GroupStructurePK(2, 4, 2));
        System.out.println(parent.getId());
        em.close();
    }
}
