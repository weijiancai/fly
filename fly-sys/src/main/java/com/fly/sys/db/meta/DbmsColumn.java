package com.fly.sys.db.meta;

import com.fly.sys.db.DBManager;
import com.fly.sys.dict.DataType;
import com.fly.sys.dict.DictCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weijiancai
 */
public class DbmsColumn implements Serializable {
    private String id;
    private String name;
    private String alias;
    private String displayName;
    private boolean isValid;
    private Date inputDate;
    private int sortNum;
    private String defaultValue;
    private boolean isNullable;
    private boolean isPk;
    private boolean isFk;
    private String fkColumnId;
    private String dataType;
    private int maxLength;
    private String comment;

    private DbmsTable table;
    private DbmsColumn fkColumn;
    private DictCode code;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        isPk = pk;
    }

    public boolean isFk() {
        return isFk;
    }

    public void setFk(boolean fk) {
        isFk = fk;
    }

    public String getFkColumnId() {
        return fkColumnId;
    }

    public void setFkColumnId(String fkColumnId) {
        this.fkColumnId = fkColumnId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public DataType getDataTypeEnum() {
        if ("varchar".equals(dataType) || "char".equals(dataType)) {
            return DataType.STRING;
        } else if ("int".equals(dataType)) {
            return DataType.INTEGER;
        } else if ("datetime".equals(dataType) || "date".equals(dataType) || "timestamp".equals(dataType)) {
            return DataType.DATE;
        } else if ("decimal".equals(dataType)) {
            return DataType.NUMBER;
        } else if ("double".equals(dataType)) {
            return DataType.DOUBLE;
        }

        return DataType.STRING;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DbmsTable getTable() {
        return table;
    }

    public void setTable(DbmsTable table) {
        this.table = table;
    }

    public DbmsColumn getFkColumn() {
        if (fkColumn == null) {
            fkColumn = DBManager.getColumnById(fkColumnId);
        }
        return fkColumn;
    }

    public void setFkColumn(DbmsColumn fkColumn) {
        this.fkColumn = fkColumn;
    }

    public DictCode getCode() {
        return code;
    }

    public void setCode(DictCode code) {
        this.code = code;
    }

    public String getNameKey() {
        return getTable().getNameKey() + "." + getName();
    }
}
