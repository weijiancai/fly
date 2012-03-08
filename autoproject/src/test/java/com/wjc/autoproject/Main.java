package com.wjc.autoproject;

import org.jdom.JDOMException;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author weijiancai
 */
public class Main {
    public static void main(String[] args) throws JDOMException, IOException {
        if (args.length != 1) {
            throw new RuntimeException("请输入项目自动生成XML文件！");
        }

        String xmlPath = args[0];
        File xmlFile = new File(xmlPath);
        if (!xmlFile.exists()) {
            throw new RuntimeException("此文件【" + xmlPath + "】不存在！");
        }

        AutoParser autoParser = new AutoParser(xmlFile);
    }
}
