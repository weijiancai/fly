package com.fly.sys.web.listener;

import com.fly.sys.clazz.ClassDefLoader;
import com.fly.sys.db.DBManager;
import com.fly.sys.dict.CodeManager;
import com.fly.sys.module.ModuleManager;
import com.fly.sys.project.ProjectManager;
import com.fly.sys.util.UFile;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * 系统ServletContext监听器
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class SystemContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try { // 初始化配置信息
            DBManager.init();
            CodeManager.init();
            ClassDefLoader.init();
            ModuleManager.init();
            ProjectManager.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UFile.WEB_INF = new File(servletContextEvent.getServletContext().getRealPath("/WEB-INF"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
