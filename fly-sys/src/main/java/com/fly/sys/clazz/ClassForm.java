package com.fly.sys.clazz;

/**
 * @author weijiancai
 */
public class ClassForm {
    private String id;
    private String classId;
    private String name;
    private int colCount;
    private int colWidth;
    private int labelGap;
    private int fieldGap;
    private boolean isDefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
    }

    public int getLabelGap() {
        return labelGap;
    }

    public void setLabelGap(int labelGap) {
        this.labelGap = labelGap;
    }

    public int getFieldGap() {
        return fieldGap;
    }

    public void setFieldGap(int fieldGap) {
        this.fieldGap = fieldGap;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ClassForm");
        sb.append("{id='").append(id).append('\'');
        sb.append(", classId='").append(classId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", colCount=").append(colCount);
        sb.append(", colWidth=").append(colWidth);
        sb.append(", labelGap=").append(labelGap);
        sb.append(", fieldGap=").append(fieldGap);
        sb.append(", isDefault=").append(isDefault);
        sb.append('}');
        return sb.toString();
    }
}
