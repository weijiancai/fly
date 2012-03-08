package com.wjc.autoproject;

import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class AutoCoder {
    private Coder coder;
    private FreeMarkerConfiguration freeMarkerConfiguration = FreeMarkerConfiguration.getInstance();
    private File mainBasePackageDir;
    private File testBasePackageDir;

    public AutoCoder(Coder coder) {
        this.coder = coder;

        genCode();
    }

    private void genCode() {
        if (coder.isUseMaven()) {
            genMaven();
        } else {
            // 创建基本包目录
            String basePackage = coder.getBasePackage();
            if (StringUtil.isNotEmpty(basePackage)) {
                mainBasePackageDir = new File(coder.getBaseDir(), basePackage);
                //testBasePackageDir = new File(coder.getBaseDir(), "test/" + basePackage);
                mainBasePackageDir.mkdirs();
                //testBasePackageDir.mkdirs();
            }
        }
    }

    public void genMaven() {
        File src_main_java_dir = new File(coder.getBaseDir(), "src/main/java");
        File src_main_resources_dir = new File(coder.getBaseDir(), "src/main/resources");
        File src_site_dir = new File(coder.getBaseDir(), "src/site");
        File src_test_java_dir = new File(coder.getBaseDir(), "src/test/java");
        File src_test_resources_dir = new File(coder.getBaseDir(), "src/test/resources");
        src_main_java_dir.mkdirs();
        src_main_resources_dir.mkdirs();
        src_site_dir.mkdirs();
        src_test_java_dir.mkdirs();
        src_test_resources_dir.mkdirs();


        if ("war".equals(coder.getPom_packaging())) {
            File src_main_webapp_dir = new File(coder.getBaseDir(), "src/main/webapp");
            if (!src_main_webapp_dir.exists()) {
                src_main_webapp_dir.mkdirs();
            }
        }

        String basePackage = coder.getPom_groupId().replace(".", "/") + "/" + coder.getPom_artifactId();
        mainBasePackageDir = new File(src_main_java_dir, basePackage);
        testBasePackageDir = new File(src_test_java_dir, basePackage);
        mainBasePackageDir.mkdirs();
        testBasePackageDir.mkdirs();

        genFile("pom.ftl", coder.getBaseDir(), "pom.xml");
    }

    private void genFile(String tplName, File baseDir, String fileName) {
        Template template = getTemplate(tplName);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("coder", coder);
            template.process(map, new FileWriter(new File(baseDir, fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Template getTemplate(String name) {
        try {
            return freeMarkerConfiguration.getTemplate(name);
        } catch (IOException e) {
            throw new RuntimeException("模版没有找到", e);
        }
    }
}
