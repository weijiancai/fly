package com.fly.sys.config;

import com.fly.common.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 系统信息
 *
 * @author weijiancai
 */
public class SysInfo {
    private static Properties infoProp = new Properties();
    private static File infoFile;
    private static final String INFO_FILE_PATH = "/info.xml";

    public static boolean isInit = false;

    public static void store() {
        try {
            infoProp.storeToXML(new FileOutputStream(infoFile), "Sys Info", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        // 从类路径中装载系统信息
        try {
            infoFile = FileUtil.getFileFromClassPath(INFO_FILE_PATH);
            if (null == infoFile) {
                infoFile = new File(new File(SysInfo.class.getResource("/").toURI()), INFO_FILE_PATH);
                infoProp.setProperty("isInit", "false");
                store();
            } else {
                infoProp.loadFromXML(new FileInputStream(infoFile));

                String sIsInit = infoProp.getProperty("isInit");
                isInit = "true".equalsIgnoreCase(sIsInit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
