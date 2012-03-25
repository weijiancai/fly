package com.fly.sys.clazz;

import com.fly.sys.IPDB;

import java.util.List;
import java.util.Map;

/**
 * 类定义信息
 *
 * @author weijiancai
 */
public class ClassDef implements IPDB {
    private String id;
    private String name;
    private String cname;
    private String superClass;
    private String author;
    private String desc;
    private String version;
    private List<ClassForm> formList;
    private List<Field> fieldList;

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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public List<ClassForm> getFormList() {
        return formList;
    }

    public void setFormList(List<ClassForm> formList) {
        this.formList = formList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ClassDef");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", cname='").append(cname).append('\'');
        sb.append(", superClass='").append(superClass).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", formList=").append(formList);
        sb.append(", fieldList=").append(fieldList);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Map<String, Map<String, Object>> getPDBMap() {
        return null;
    }
}
