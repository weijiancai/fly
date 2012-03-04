package com.fly.base.db.util;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author weijiancai
 */
public class FileUtil {
    public static File getFileFromClassPath(String path) throws URISyntaxException {
        return new File(FileUtil.class.getResource(path).toURI());
    }
}
