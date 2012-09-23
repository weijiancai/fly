package com.fly.sys.project;

import com.alibaba.fastjson.JSON;
import com.fly.sys.dict.DictCategory;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class ProjectDefine implements Serializable {
    private String id;
    private String name;
    private String projectDesc;
    private String packageName;
    private String projectUrl;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private List<ProjectModule> moduleList;
    private DictCategory rootCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public List<ProjectModule> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ProjectModule> moduleList) {
        this.moduleList = moduleList;
    }

    public DictCategory getRootCategory() {
        return rootCategory;
    }

    public void setRootCategory(DictCategory rootCategory) {
        this.rootCategory = rootCategory;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, true);
    }
}
