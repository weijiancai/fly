package com.fly.base.pm.entity;

import com.fly.base.persist.Persistence;
import com.fly.base.persist.PersistenceFactory;
import org.junit.Test;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class GroupTypeTest {
    @Test
    public void testSaveGroupType() throws Exception {
        Persistence<GroupType> persistence = PersistenceFactory.getJpaPersistence();
        persistence.save(new GroupType("集团总部"),
                new GroupType("区域总部"),
                new GroupType("分公司"),
                new GroupType("销售点")
        );
    }
}
