package com.fly.common.util;

import java.io.File;
import java.net.URL;

/**
 * @author weijiancai
 */
public class FileUtil {
    public static File getFileFromClassPath(String path) throws Exception {
        return getFileFromClassPath(path, false);
    }

    public static File getFileFromClassPath(String path, boolean autoCreate) throws Exception {
        URL url = FileUtil.class.getResource(path);
        if (url == null) {
            if (autoCreate) {
                File file = new File(new File(FileUtil.class.getResource("/").toURI()), path);
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
        return new File(FileUtil.class.getResource(path).toURI());
    }
}
