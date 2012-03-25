package com.fly.sys.dict;

import com.fly.sys.db.PO;
import com.fly.sys.util.UUIDUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class Code implements PO {
    private String id;
    private String name;
    private String value;
    private String categoryId;
    private boolean isValid;
    private int sortNum;
    private Date inputDate;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Code");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", inputDate=").append(inputDate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Map<String, Object> getPoMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", UUIDUtil.getUUID());
        map.put("name", getName());
        map.put("value", getValue());
        map.put("category_id", getCategoryId());
        map.put("is_valid", isValid() ? "T" : "F");
        map.put("sort_num", getSortNum());
        map.put("input_date", getInputDate());

        return map;
    }
}
