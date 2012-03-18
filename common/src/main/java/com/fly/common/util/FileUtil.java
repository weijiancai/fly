package com.fly.common.util;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author weijiancai
 */
public class FileUtil {
    public static File getFileFromClassPath(String path) throws URISyntaxException {
        System.out.println(FileUtil.class.getResource(path).toURI());
        return new File(FileUtil.class.getResource(path).toURI());
    }
}
