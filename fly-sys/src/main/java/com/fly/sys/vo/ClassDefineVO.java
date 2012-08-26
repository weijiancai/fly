package com.fly.sys.vo;

import com.fly.sys.clazz.ClassDefine;
import com.fly.sys.clazz.ClassField;
import com.fly.sys.clazz.ClassForm;
import com.fly.sys.dict.DictCode;

import java.util.*;

/**
 * 类定义 VO 对象
 *
 * @author weijiancai
 * @since 1.0.0
 */
public class ClassDefineVO {
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

    private TableVO classTable;
    private FormVO queryForm;
    private FormVO editForm;

    private Map<String, List<DictCodeVO>> dictMap = new HashMap<String, List<DictCodeVO>>();

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

    public TableVO getClassTable() {
        return classTable;
    }

    public void setClassTable(TableVO classTable) {
        this.classTable = classTable;
    }

    public FormVO getQueryForm() {
        return queryForm;
    }

    public void setQueryForm(FormVO queryForm) {
        this.queryForm = queryForm;
    }

    public FormVO getEditForm() {
        return editForm;
    }

    public void setEditForm(FormVO editForm) {
        this.editForm = editForm;
    }

    public Map<String, List<DictCodeVO>> getDictMap() {
        return dictMap;
    }

    public void setDictMap(Map<String, List<DictCodeVO>> dictMap) {
        this.dictMap = dictMap;
    }

    public void addFieldDict(String fieldName, List<DictCodeVO> codeList) {
        dictMap.put(fieldName, codeList);
    }

    public static ClassDefineVO getInstance(ClassDefine classDefine) {
        ClassDefineVO vo = new ClassDefineVO();
        vo.setAuthor(classDefine.getAuthor());
        vo.setCname(classDefine.getCname());
        vo.setDesc(classDefine.getDesc());
        vo.setId(classDefine.getId());
        vo.setInputDate(classDefine.getInputDate());
        vo.setName(classDefine.getName());
        vo.setSortNum(classDefine.getSortNum());
        vo.setSuperClass(classDefine.getSuperClass());
        vo.setValid(classDefine.isValid());
        vo.setVersion(classDefine.getVersion());

        for (ClassForm form : classDefine.getFormList()) {
            if ("0".equals(form.getFormType())) { // 查询form
                vo.setQueryForm(FormVO.getInstance(form));
            } else if ("1".equals(form.getFormType())) {  // 编辑form
                vo.setEditForm(FormVO.getInstance(form));
            }
        }
        if (classDefine.getClassTableList() != null && classDefine.getClassTableList().size() > 0) {
            vo.setClassTable(TableVO.getInstance(classDefine.getClassTableList().get(0)));
        }

        for (ClassField field : classDefine.getFieldList()) {
            if (field.getDictCategory() != null) {
                List<DictCodeVO> codeList = new ArrayList<DictCodeVO>();
                for (DictCode code : field.getDictCategory().getCodeList()) {
                    codeList.add(DictCodeVO.getInstance(code));
                }
                vo.addFieldDict(field.getName().toLowerCase(), codeList);
            }
        }

        return vo;
    }
}
