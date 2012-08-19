package com.fly.sys.clazz;

import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.dict.CodeManager;
import com.fly.sys.dict.DictCategory;

import java.io.Serializable;
import java.util.Date;

/**
 * 字段定义
 *
 * @author weijiancai
 */
public class ClassField implements Serializable {
    private String id;
    private String name;
    private String fieldDesc;
    private String type;
    private String dzCategoryId;
    private Date inputDate;
    private boolean isValid;
    private int sortNum;

    private ClassDefine classDef;
    private DbmsColumn column;
    private DictCategory dictCategory;

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

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDzCategoryId() {
        return dzCategoryId;
    }

    public void setDzCategoryId(String dzCategoryId) {
        this.dzCategoryId = dzCategoryId;
        this.dictCategory = CodeManager.getDictCategoryById(dzCategoryId);
    }

    public ClassDefine getClassDef() {
        return classDef;
    }

    public void setClassDef(ClassDefine classDef) {
        this.classDef = classDef;
    }

    public DbmsColumn getColumn() {
        return column;
    }

    public void setColumn(DbmsColumn column) {
        this.column = column;
    }

    public DictCategory getDictCategory() {
        if (dictCategory == null) {
            return CodeManager.getDictCategoryById(dzCategoryId);
        }
        return dictCategory;
    }

    public void setDictCategory(DictCategory dictCategory) {
        this.dictCategory = dictCategory;
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
}
