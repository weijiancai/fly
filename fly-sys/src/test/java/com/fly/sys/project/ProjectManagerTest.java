package com.fly.sys.project;

import com.fly.sys.config.SysInfo;
import org.junit.Test;

/**
 * @author weijiancai
 */
public class ProjectManagerTest {
    @Test
    public void testGetProject() throws Exception {
        SysInfo.setProjectDefInit(false);
        ProjectDefine project = ProjectManager.getProject("FLY-SYS");
        System.out.println(project);
    }
}
