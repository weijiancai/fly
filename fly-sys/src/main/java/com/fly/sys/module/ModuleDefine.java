package com.fly.sys.module;

import com.fly.sys.clazz.ClassDefine;
import com.fly.sys.clazz.ClassManager;
import com.fly.sys.project.ProjectDefine;

import java.util.Date;
import java.util.List;

/**
 * @author weijiancai
 */
public class ModuleDefine {
    private String id;
    private String name;
    private String displayName;
    private String superModuleId;
    private String classId;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ModuleDefine superModule;
    private ClassDefine classDefine;
    private List<ProjectDefine> projectList;

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
        if (superModule == null) {
            superModule = ModuleManager.getModuleById(superModuleId);
        }
        return superModule;
    }

    public void setSuperModule(ModuleDefine superModule) {
        this.superModule = superModule;
    }

    public List<ProjectDefine> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDefine> projectList) {
        this.projectList = projectList;
    }

    public ClassDefine getClassDefine() {
        if (classDefine == null) {
            classDefine = ClassManager.getClassDefineById(classId);
        }
        return classDefine;
    }

    public void setClassDefine(ClassDefine classDefine) {
        this.classDefine = classDefine;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ModuleDefine");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", superModuleId='").append(superModuleId).append('\'');
        sb.append(", inputDate=").append(inputDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", superModule=").append(superModule);
        sb.append(", classDefine=").append(classDefine);
        sb.append(", projectList=").append(projectList);
        sb.append('}');
        return sb.toString();
    }
}
