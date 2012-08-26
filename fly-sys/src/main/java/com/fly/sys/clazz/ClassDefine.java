package com.fly.sys.clazz;

import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.util.UString;
import com.fly.sys.vo.FormVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类定义信息
 *
 * @author weijiancai
 */
public class ClassDefine implements Serializable {
    private String id;
    private String name;
    private String cname;
    private String superClass;
    private String author;
    private String desc;
    private String version;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private List<ClassForm> formList;
    private List<ClassField> fieldList;
    private List<RClassDbmsTable> dbmsTableList;
    private List<ClassTable> classTableList;
    private List<ClassQuery> classQueryList;

    private List<String> itemClassNameList = new ArrayList<String>();

    private Map<String, ClassField> fieldMap;
    private Map<String, ClassForm> formMap;

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

    public List<ClassField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<ClassField> fieldList) {
        this.fieldList = fieldList;
    }

    public List<ClassForm> getFormList() {
        return formList;
    }

    public ClassForm getDefaultForm() {
        if (formList != null && formList.size() > 0) {
            for (ClassForm form : formList) {
                if ("default".equals(form.getName())) {
                    return form;
                }
            }
            return formList.get(0);
        }

        return null;
    }

    public void setFormList(List<ClassForm> formList) {
        this.formList = formList;
    }

    public Map<String, ClassField> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, ClassField> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, ClassForm> getFormMap() {
        return formMap;
    }

    public void setFormMap(Map<String, ClassForm> formMap) {
        this.formMap = formMap;
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

    public List<RClassDbmsTable> getDbmsTableList() {
        return dbmsTableList;
    }

    public void setDbmsTableList(List<RClassDbmsTable> dbmsTableList) {
        this.dbmsTableList = dbmsTableList;
    }

    public List<ClassTable> getClassTableList() {
        return classTableList;
    }

    public void setClassTableList(List<ClassTable> classTableList) {
        this.classTableList = classTableList;
    }

    public List<ClassQuery> getClassQueryList() {
        return classQueryList;
    }

    public void setClassQueryList(List<ClassQuery> classQueryList) {
        this.classQueryList = classQueryList;
    }

    public List<String> getItemClassNameList() {
        return itemClassNameList;
    }

    public void addItemClassName(String className) {
        itemClassNameList.add(className);
    }

    public void addClassForm(ClassForm form) {
        if (formList == null) {
            formList = new ArrayList<ClassForm>();
        }
        formList.add(form);
    }

    public ClassForm getEditForm() {
        for (ClassForm form : this.getFormList()) {
            if ("1".equals(form.getFormType())) {  // 编辑form
                return form;
            }
        }

        return null;
    }

    public ClassForm getQueryForm() {
        for (ClassForm form : this.getFormList()) {
            if ("0".equals(form.getFormType())) { // 查询form
                return form;
            }
        }

        return null;
    }

    /**
     * 获取查询sql语句，不含查询条件
     *
     * @return 返回查询sql语句
     */
    public String getQuerySql() {
        if (classTableList != null && classTableList.size() > 0) {
            return classTableList.get(0).getSql();
        }

        return "";
    }

    /**
     * 根据数据库表名，获取数据库表信息
     *
     * @return 返回数据库表信息
     */
    public DbmsTable getDbTableByName(String name) {
        for (RClassDbmsTable cdt : getDbmsTableList()) {
            if (name.equalsIgnoreCase(cdt.getDbmsTable().getName())) {
                return cdt.getDbmsTable();
            }
        }
        return null;
    }

    /**
     * 获取列字符串信息，例如 表名.列名 别名,表名.列名 别名,表名.列名 别名
     *
     * @return 返回列字符串信息
     */
    public String getColumnStr() {
        StringBuilder sb = new StringBuilder();
        for (ClassField field : getFieldList()) {
            sb.append(",").append(field.getColumn().getTable().getName()).append(".").append(field.getColumn().getName()).append(" ").append(field.getName().toLowerCase());
        }
        return sb.substring(1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ClassDefine");
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
}
