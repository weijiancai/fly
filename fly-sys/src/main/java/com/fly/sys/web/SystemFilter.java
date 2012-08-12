package com.fly.sys.web;

import com.fly.sys.clazz.ClassDefLoader;
import com.fly.sys.db.DBManager;
import com.fly.sys.module.ModuleManager;
import com.fly.sys.project.ProjectManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author weijiancai
 */
public class SystemFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Request URL: " + ((HttpServletRequest)req).getRequestURL());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws javax.servlet.ServletException {

    }
}
