package com.wjc.autoproject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.File;

/**
 * @author weijiancai
 */
public class Coder {
    private String projectName;
    private File baseDir;
    private String basePackage;
    private boolean useMaven;
    private String pom_groupId;
    private String pom_artifactId;
    private String pom_version;
    private String pom_packaging;
    private String po_package;

    private boolean genPo; // 是否生成PO

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        if (null != projectName && projectName.trim().length() > 0) {
            this.projectName = projectName;
        }
    }

    public File getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(File baseDir) {
        if (null != baseDir) {
            this.baseDir = baseDir;
        }
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        if (StringUtil.isNotEmpty(basePackage)) {
            this.basePackage = basePackage.replace(".", "/");
        }
    }

    public boolean isUseMaven() {
        return useMaven;
    }

    public void setUseMaven(boolean useMaven) {
        this.useMaven = useMaven;
    }

    public String getPom_groupId() {
        return pom_groupId;
    }

    public void setPom_groupId(String pom_groupId) {
        if (StringUtil.isNotEmpty(pom_groupId)) {
            this.pom_groupId = pom_groupId;
        }
    }

    public String getPom_artifactId() {
        return pom_artifactId;
    }

    public void setPom_artifactId(String pom_artifactId) {
        if (StringUtil.isNotEmpty(pom_artifactId)) {
            this.pom_artifactId = pom_artifactId;
        }
    }

    public String getPom_version() {
        return pom_version;
    }

    public void setPom_version(String pom_version) {
        if (StringUtil.isNotEmpty(pom_version)) {
            this.pom_version = pom_version;
        }
    }

    public String getPom_packaging() {
        return pom_packaging;
    }

    public void setPom_packaging(String pom_packaging) {
        if (StringUtil.isNotEmpty(pom_packaging)) {
            this.pom_packaging = pom_packaging;
        }
    }

    public String getPo_package() {
        return po_package;
    }

    public void setPo_package(String po_package) {
        this.po_package = po_package;
    }

    public boolean isGenPo() {
        return genPo;
    }

    public void setGenPo(boolean genPo) {
        this.genPo = genPo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
