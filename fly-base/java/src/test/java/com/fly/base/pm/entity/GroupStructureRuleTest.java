package com.fly.base.pm.entity;

import com.fly.base.persist.Persistence;
import com.fly.base.persist.PersistenceFactory;
import org.junit.Test;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class GroupStructureRuleTest {
    @Test
    public void testSaveGroupStructureRule() throws Exception {
        Persistence<GroupStructureRule> persistence = PersistenceFactory.getJpaPersistence();
        persistence.save(
                new GroupStructureRule(new GroupStructureRulePK(2, 2, 1)),
                new GroupStructureRule(new GroupStructureRulePK(2, 3, 2)),
                new GroupStructureRule(new GroupStructureRulePK(2, 4, 3))
        );
    }
}
