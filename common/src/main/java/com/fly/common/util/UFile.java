package com.fly.common.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author weijiancai
 */
public class UFile {
    public static File getFileFromClassPath(String path) throws Exception {
        return getFileFromClassPath(path, false);
    }

    public static File getFileFromClassPath(String path, boolean autoCreate) throws Exception {
        URL url = UFile.class.getClassLoader().getResource(path);
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
        URL url = UFile.class.getClassLoader().getResource(path);
        if (null != url) {
            return url.toURI();
        }

        return null;
    }
}
