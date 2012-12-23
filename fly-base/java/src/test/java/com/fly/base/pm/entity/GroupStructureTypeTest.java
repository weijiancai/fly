package com.fly.base.pm.entity;

import com.fly.base.persist.Persistence;
import com.fly.base.persist.PersistenceFactory;
import org.junit.Test;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class GroupStructureTypeTest {
    @Test
    public void testSaveGroupStructureType() throws Exception {
        Persistence<GroupStructureType> persistence = PersistenceFactory.getJpaPersistence();
        persistence.save(
                new GroupStructureType("行政组织结构"),
                new GroupStructureType("生产组织结构"),
                new GroupStructureType("销售组织结构"),
                new GroupStructureType("产品线 - 打印机"),
                new GroupStructureType("产品线 - 扫描仪")
        );
    }

    @Test
    public void testClearTable() throws Exception {
        Persistence<GroupStructureType> persistence = PersistenceFactory.getJpaPersistence();
        persistence.clear(GroupStructureType.class);
    }
}
