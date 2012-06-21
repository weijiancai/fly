package com.fly.fxsys;

import com.fly.ApplicationRunner;
import com.fly.sys.project.ProjectDefine;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ProjectDefEndToEnd {
    private ApplicationRunner application = new ApplicationRunner();

    @Before
    public void setUp() throws Exception {
        application.start();
    }

    @Test
    public void createProductByClass() {
        ProjectDefine projectDefine = getProjectDefine();

    }

    public ProjectDefine getProjectDefine() {
        ProjectDefine project = new ProjectDefine();
        project.setName("FLY SYS 测试项目");

        return project;
    }
}
