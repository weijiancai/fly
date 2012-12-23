package com.fly.base.pm.entity;

import com.fly.base.persist.Persistence;
import com.fly.base.persist.PersistenceFactory;
import org.junit.Test;

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
}
