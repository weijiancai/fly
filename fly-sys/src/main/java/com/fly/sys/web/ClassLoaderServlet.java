package com.fly.sys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fly.sys.clazz.ClassDefine;
import com.fly.sys.clazz.ClassManager;
import com.fly.sys.clazz.Query;
import com.fly.sys.util.UString;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ClassLoaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String classDefName = UString.substringBefore(request.getRequestURI().substring(1), ".class");
        ClassDefine classDefine;
        if ("ProjectModule".equalsIgnoreCase(classDefName)) {
            classDefine = ClassManager.getClassDefine(classDefName);
        } else {
            classDefine = ClassManager.getClassDefine(classDefName);
        }
        String method = request.getParameter("method");
        if ("query".equals(method)) {
            String conditions = decode(request.getParameter("conditions"));
            String values = decode(request.getParameter("values"));
            Query query = new Query(classDefine);
            List<Map<String, Object>> list = query.list(conditions, JSON.parseArray(values));
            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } else if ("update".equals(method)) {
            String values = decode(request.getParameter("valueMap"));
            String conditions = decode(request.getParameter("conditionMap"));
            String tableName = request.getParameter("tableName");
            Query query = new Query(classDefine);
            query.update(JSON.parseObject(values), JSON.parseObject(conditions), tableName);
        } else {
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(classDefine));
            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            oos.writeObject(classDefine);
            oos.flush();
            oos.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String decode(String str) throws IOException {
//        return URLDecoder.decode(str, "UTF-8");
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(str), "UTF-8");
    }
}
