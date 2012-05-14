package com.fly.sys.project;

import com.fly.sys.module.ModuleDefine;
import com.fly.sys.module.ModuleManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class ProjectModule {
    private String projectId;
    private String displayName;
    private String moduleId;
    private String superModuleId;
    private int level;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ProjectDefine project;
    private ModuleDefine module;
    private ModuleDefine superModule;
    private List<ProjectModule> children;

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

    public ModuleDefine getSuperModule() {
        return superModule;
    }

    public void setSuperModule(ModuleDefine superModule) {
        this.superModule = superModule;
    }

    public ProjectDefine getProject() {
        return project;
    }

    public void setProject(ProjectDefine project) {
        this.project = project;
    }

    public ModuleDefine getModule() {
        return module;
    }

    public void setModule(ModuleDefine module) {
        this.module = module;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<ProjectModule> getChildren() {
        /*if (children == null) {
            children = ProjectManager.getChildrenModule(projectId, module.getId());
        }*/
        return children;
    }

    public void setChildren(List<ProjectModule> children) {
        this.children = children;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
