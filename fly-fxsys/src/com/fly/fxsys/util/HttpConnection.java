package com.fly.fxsys.util;

import com.alibaba.fastjson.JSON;
import com.fly.fxsys.control.FxBase;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class HttpConnection {
    private String urlStr;

    public HttpConnection(String url) {
        this.urlStr = url;
    }

    public String getContentStr() throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.connect();

        System.out.println(conn.getContentType());
        System.out.println(conn.getResponseCode());
        System.out.println(conn.getResponseMessage());
        System.out.println(conn.getContentLength());
        InputStream in = conn.getInputStream();
        System.out.println(in.available());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();

        return sb.toString();
    }

    public Object getObject() throws IOException, ClassNotFoundException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.connect();
        InputStream in = conn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        Object obj = ois.readObject();
        ois.close();

        return  obj;
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> query(String className, Map<String, String> map) throws IOException, ClassNotFoundException {
        URL url = new URL(FxBase.URL + "/" + className + ".class");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
//        conn.connect();
        String params = String.format("query=%s", JSON.toJSONString(map));
        conn.getOutputStream().write(params.getBytes());
        conn.getOutputStream().flush();
        conn.getOutputStream().close();
        int code = conn.getResponseCode();
        System.out.println("Response Code = " + code);

        InputStream in = conn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        Object obj = ois.readObject();
        ois.close();

        return  (List<Map<String, Object>>)obj;
    }
}
