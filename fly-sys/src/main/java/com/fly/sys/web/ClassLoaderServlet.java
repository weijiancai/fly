package com.fly.sys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fly.sys.clazz.ClassDefine;
import com.fly.sys.clazz.ClassManager;
import com.fly.sys.clazz.Query;
import com.fly.sys.db.QueryResult;
import com.fly.sys.db.query.QueryCondition;
import com.fly.sys.dict.QueryMode;
import com.fly.sys.util.UString;
import com.fly.sys.vo.AjaxResultVO;
import com.fly.sys.vo.ClassDefineVO;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @author weijiancai
 */
public class ClassLoaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        boolean isBrowser = userAgent.contains("firefox") || userAgent.contains("msie");
        if (isBrowser) {
            response.setContentType("application/json;charset=UTF-8");
        }
        String classDefName = UString.substringBefore(request.getRequestURI().substring(1), ".class");
        ClassDefine classDefine;
        if ("ProjectModule".equalsIgnoreCase(classDefName)) {
            classDefine = ClassManager.getClassDefine(classDefName);
        } else {
            classDefine = ClassManager.getClassDefine(classDefName);
        }
        String method = request.getParameter("method");
        if ("query".equals(method)) {
            if (isBrowser) {
                String conditions = request.getParameter("conditions");
                int page = 1;
                int rows = 20;
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                    rows = Integer.parseInt(request.getParameter("rows"));
                } catch (NumberFormatException e) {
//                    e.printStackTrace();
                }
                if (UString.isNotEmpty(conditions)) {
                    JSONArray array = JSON.parseArray(conditions);
                    JSONObject object;
                    QueryCondition queryCondition = new QueryCondition();
                    for (int i = 0; i < array.size(); i++) {
                        object = array.getJSONObject(i);
                        queryCondition.addCondition(object.getString("name"), QueryMode.get(object.getIntValue("queryMode")), object.getString("value"));
                    }
                    Query query = new Query(classDefine);
                    QueryResult<Map<String, Object>> queryResult = query.list(queryCondition.getConditions(), queryCondition.getValueList(), page, rows);
                    response.getWriter().write(JSON.toJSONString(queryResult, SerializerFeature.WriteDateUseDateFormat));
                } else {
                    response.getWriter().write("[]");
                }
            } else {
                String conditions = decode(request.getParameter("conditions"));
                String values = decode(request.getParameter("values"));
                Query query = new Query(classDefine);
                QueryResult<Map<String, Object>> list = query.list(conditions, JSON.parseArray(values));
                ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
                oos.writeObject(list.getRows());
                oos.flush();
                oos.close();
            }
        } else if ("update".equals(method)) {
            if (isBrowser) {
                String values = request.getParameter("values");
                if (UString.isNotEmpty(values)) {
                    Query query = new Query(classDefine);
                    try {
                        query.update(JSON.parseObject(values), "update");
                        writeJsonObject(response, new AjaxResultVO(true));
                    } catch (Exception e) {
                        e.printStackTrace();
                        writeJsonObject(response, new AjaxResultVO(false, e.getMessage()));
                    }
                }
            } else {
                String values = decode(request.getParameter("valueMap"));
                String conditions = decode(request.getParameter("conditionMap"));
                String tableName = request.getParameter("tableName");
                Query query = new Query(classDefine);
                query.update(JSON.parseObject(values), JSON.parseObject(conditions), tableName);
            }
        } else if ("add".equals(method)) {
            if (isBrowser) {
                String values = request.getParameter("values");
                if (UString.isNotEmpty(values)) {
                    Query query = new Query(classDefine);
                    try {
                        query.update(JSON.parseObject(values), "save");
                        writeJsonObject(response, new AjaxResultVO(true));
                    } catch (Exception e) {
                        e.printStackTrace();
                        writeJsonObject(response, new AjaxResultVO(false, e.getMessage()));
                    }
                }
            }
        } else if ("delete".equals(method)) {
            if (isBrowser) {
                String values = request.getParameter("rowData");
                if (UString.isNotEmpty(values)) {
                    Query query = new Query(classDefine);
                    try {
                        query.update(JSON.parseObject(values), "delete");
                        writeJsonObject(response, new AjaxResultVO(true));
                    } catch (Exception e) {
                        e.printStackTrace();
                        writeJsonObject(response, new AjaxResultVO(false, e.getMessage()));
                    }
                }
            }
        } else {
            if (isBrowser) {
                response.getWriter().write(JSON.toJSONString(ClassDefineVO.getInstance(classDefine)));
            } else {
                ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
                oos.writeObject(classDefine);
                oos.flush();
                oos.close();
            }
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

    private void writeJsonObject(HttpServletResponse response, Object obj) throws IOException {
        response.getWriter().write(JSON.toJSONString(obj));
    }
}
