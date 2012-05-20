package com.fly.fxsys.control.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ModuleMenuItem {
    private String projectId;
    private String displayName;
    private String moduleId;
    private String superModuleId;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    public ModuleMenuItem(JSONObject obj) {
        projectId = obj.getString("projectId");
        displayName = obj.getString("displayName");
        moduleId = obj.getString("moduleId");
        superModuleId = obj.getString("superModuleId");
        inputDate = obj.getDate("inputDate");
        isValid = obj.getBoolean("valid");
        sortNum = obj.getInteger("sortNum");
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getSuperModuleId() {
        return superModuleId;
    }

    public void setSuperModuleId(String superModuleId) {
        this.superModuleId = superModuleId;
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

    @Override
    public String toString() {
        return displayName;
    }
}
