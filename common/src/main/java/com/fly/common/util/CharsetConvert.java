package com.fly.common.util;

import java.io.*;

/**
 * 字符集转换工具类
 *
 * @author weijiancai
 */
public class CharsetConvert {
    private String oldCharset;  // 原字符集
    private String newCharset;  // 新字符集
    /**
     * 构造函数
     * @param oldCharset 原字符集
     * @param newCharset 新字符集
     * */
    public CharsetConvert(String oldCharset, String newCharset) {
        this.oldCharset = oldCharset;
        this.newCharset = newCharset;
    }

    /**
     * 转换文件字符集
     * @param file 路径字符串，如果此file是个目录，递归转换此目录下的所有java文件
     * @throws Exception  Exception
     **/
    public void convert(String file) throws Exception {
        File f = new File(file);
        if(f.isDirectory()) {
            convertDir(f);
        } else {
            convertFile(f);
        }
    }

    /**
     * 转换文件字符集
     * @param baseDir 文件目录，递归转换此目录下的所有java文件
     * @throws IOException  IOException
     **/
    public void convertDir(File baseDir) throws IOException {
        File[] files = baseDir.listFiles();
        for(File file : files) {
            if(file.isDirectory()) {
                convertDir(file);
            } else {
                if(file.getName().endsWith(".java")) {
                    convertFile(file);
                }
            }
        }
    }

    /**
     * 转换文件字符集
     * @param file 需要转换的文件
     * @throws IOException IOException
     **/
    public void convertFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), oldCharset));
        StringBuilder content = new StringBuilder();
        String line;

        while((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();

        PrintWriter pw = new PrintWriter(file, newCharset);
        pw.write(content.toString());
        pw.flush();
        pw.close();
    }
}
