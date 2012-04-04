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

    private static boolean isDbmsInit = false;
    private static boolean isClassDefInit = false;

    public static void store() {
        try {
            infoProp.storeToXML(new FileOutputStream(infoFile), "Sys Info", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDbmsInit() {
        return isDbmsInit;
    }

    public static void setDbmsInit(boolean dbmsInit) {
        isDbmsInit = dbmsInit;
        infoProp.setProperty("isDbmsInit", dbmsInit + "");
    }

    public static boolean isClassDefInit() {
        return isClassDefInit;
    }

    public static void setClassDefInit(boolean classDefInit) {
        isClassDefInit = classDefInit;
        infoProp.setProperty("isClassDefInit", classDefInit + "");
    }

    static {
        // 从类路径中装载系统信息
        try {
            infoFile = FileUtil.getFileFromClassPath(INFO_FILE_PATH);
            if (null == infoFile) {
                infoFile = new File(new File(SysInfo.class.getResource("/").toURI()), INFO_FILE_PATH);
                infoProp.setProperty("isDbmsInit", "false");
                infoProp.setProperty("isClassDefInit", "false");
                store();
            } else {
                infoProp.loadFromXML(new FileInputStream(infoFile));

                String sIsDbmsInit = infoProp.getProperty("isDbmsInit");
                isDbmsInit = "true".equalsIgnoreCase(sIsDbmsInit);
                String sIsClassDefInit = infoProp.getProperty("isClassDefInit");
                isClassDefInit = "true".equalsIgnoreCase(sIsClassDefInit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
