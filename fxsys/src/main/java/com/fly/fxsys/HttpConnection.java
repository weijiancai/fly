package com.fly.fxsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
}
