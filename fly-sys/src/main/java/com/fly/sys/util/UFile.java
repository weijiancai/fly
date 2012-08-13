package com.fly.sys.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author weijiancai
 */
public class UFile {
    public static File WEB_INF;
    public static File getFileFromClassPath(String path) throws Exception {
        return getFileFromClassPath(path, false);
    }

    public static File getFileFromClassPath(String path, boolean autoCreate) throws Exception {
        URL url = getURL(path);
        if (url == null) {
            if (autoCreate) {
                File file = new File(new File(getURI("/")), path);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                return file;
            } else {
                return null;
            }
        }

        return new File(getURI(path));
    }

    public static URI getURI(String path) throws URISyntaxException {
        URL url = getURL(path);
        if (null != url) {
            return url.toURI();
        }

        return null;
    }

    public static URL getURL(String path) throws URISyntaxException {
        URL url = UFile.class.getClassLoader().getResource(path);
        if (url == null) {
            url = UFile.class.getResource(path);
        }

        if (null != url) {
            return url;
        }

        return null;
    }

    public static String toString(String path) throws URISyntaxException, IOException {
        URL url = getURL(path);

        return IOUtils.toString(url, "UTF-8");
    }
}
