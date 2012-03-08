package com.wjc.autoproject;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author weijiancai
 */
public class ClassInfo {
    /** 包名 */
    private String packageName;
    /** 类名 */
    private String className;
    /** 类描述信息 */
    private String classDesc;
    /** 类导入信息列表 */
    private List<String> importList;
    /** UUID */
    private String serialVersionUID;
    /** 类字段信息列表 */
    private List<Field> fieldList;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public String getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(String serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }
}
