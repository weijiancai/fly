package com.fly.sys.web;

import com.alibaba.fastjson.JSON;
import com.fly.sys.clazz.ClassDefine;
import com.fly.sys.clazz.ClassManager;
import com.fly.sys.clazz.Query;
import com.fly.sys.util.UString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ClassLoaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classDefName = UString.substringBefore(request.getRequestURI().substring(1), ".class");
        ClassDefine classDefine;
        if ("ProjectModule".equalsIgnoreCase(classDefName)) {
            classDefine = ClassManager.getClassDefine(classDefName);
        } else {
            classDefine = ClassManager.getClassDefine(classDefName);
        }
        if (request.getParameterMap().containsKey("query")) {
            String params = request.getParameter("query");
            Query query = new Query(classDefine);
            List<Map<String, Object>> list = query.list();
            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } else {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(classDefine));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
