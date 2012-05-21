package com.fly.sys.web;

import com.fly.sys.project.ProjectDefine;
import com.fly.sys.project.ProjectManager;
import com.fly.sys.util.UString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectDefine projectDefine = ProjectManager.getProjectByUrl(UString.substringBefore(request.getRequestURL().toString(), request.getRequestURI()));
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(JSON.toJSONString(projectDefine));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            oos.writeObject(projectDefine);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
