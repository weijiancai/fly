package com.wjc.autoproject;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 */
public class AutoCoderTest {
    private static Coder coder;

    public void clear() {
        if (coder != null) {
            try {
                File[] files = coder.getBaseDir().getParentFile().listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        FileUtils.deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGenMaven() {
        coder = new Coder();
        coder.setBaseDir(new File("D:\\temp\\autoproject"));
        coder.setUseMaven(true);
        coder.setPom_groupId("com.flysoft");
        coder.setPom_artifactId("autoproject");
        coder.setPom_packaging("war");
        coder.setPom_version("0.1");
        clear();

        AutoCoder autoCoder = new AutoCoder(coder);
        File pomFile = new File(coder.getBaseDir(), "pom.xml");
        assertThat(pomFile.exists(), equalTo(true));

        File src_main_java_dir = new File(coder.getBaseDir(), "src/main/java");
        assertThat(src_main_java_dir.exists(), equalTo(true));
        File src_main_resources_dir = new File(coder.getBaseDir(), "src/main/resources");
        assertThat(src_main_resources_dir.exists(), equalTo(true));
        File src_site_dir = new File(coder.getBaseDir(), "src/site");
        assertThat(src_site_dir.exists(), equalTo(true));
        File src_test_java_dir = new File(coder.getBaseDir(), "src/test/java");
        assertThat(src_test_java_dir.exists(), equalTo(true));
        File src_test_resources_dir = new File(coder.getBaseDir(), "src/test/resources");
        assertThat(src_test_resources_dir.exists(), equalTo(true));
        File src_main_webapp_dir = new File(coder.getBaseDir(), "src/main/webapp");
        assertThat(src_main_webapp_dir.exists(), equalTo(true));

        File src_main_java_basePackageDir = new File(src_main_java_dir, "com/flysoft/autoproject");
        assertThat(src_main_java_basePackageDir.exists(), equalTo(true));
        File src_test_java_basePackageDir = new File(src_test_java_dir, "com/flysoft/autoproject");
        assertThat(src_test_java_basePackageDir.exists(), equalTo(true));
    }

    @Test
    public void testNoMaven() {
        coder = new Coder();
        coder.setBaseDir(new File("D:\\temp\\autoproject\\src"));
        coder.setUseMaven(false);
        coder.setBasePackage("com.flysoft.autoproject");
        clear();

        AutoCoder autoCoder = new AutoCoder(coder);
        File src_basePackageDir = new File(coder.getBaseDir(), "com/flysoft/autoproject");
        assertThat(src_basePackageDir.exists(), equalTo(true));
    }
}
