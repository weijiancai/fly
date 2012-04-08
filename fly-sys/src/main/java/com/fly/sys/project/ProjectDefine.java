package com.fly.sys.project;

import java.util.Date;

/**
 * @author weijiancai
 */
public class ProjectDefine {
    private String id;
    private String name;
    private String projectDesc;
    private String packageName;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ProjectDefine");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", projectDesc='").append(projectDesc).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", inputDate=").append(inputDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", sortNum=").append(sortNum);
        sb.append('}');
        return sb.toString();
    }
}
