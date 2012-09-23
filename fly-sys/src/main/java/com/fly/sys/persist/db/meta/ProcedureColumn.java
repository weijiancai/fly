package com.fly.sys.persist.db.meta;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * procedure parameter and result columns.
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
public class ProcedureColumn {
    private String columnName;
    private ProcedureColumnType columnType;
    private int dataType;
    private String typeName;
    private int precision;
    private int length;
    private short scale;
    private short radix;
    private Nullable nullable;
    private String remarks;
    private String defaultValue;
    private int sqlDataType;
    private int sqlDateTimeSub;
    private int charOctetLength;
    private int ordinalPosition;
    private String isNullable;
    private String specificName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ProcedureColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ProcedureColumnType columnType) {
        this.columnType = columnType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public short getScale() {
        return scale;
    }

    public void setScale(short scale) {
        this.scale = scale;
    }

    public short getRadix() {
        return radix;
    }

    public void setRadix(short radix) {
        this.radix = radix;
    }

    public Nullable getNullable() {
        return nullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    public void setNullable(Nullable nullable) {
        this.nullable = nullable;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @XmlTransient
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @XmlTransient
    public int getSqlDataType() {
        return sqlDataType;
    }

    public void setSqlDataType(int sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    @XmlTransient
    public int getSqlDateTimeSub() {
        return sqlDateTimeSub;
    }

    public void setSqlDateTimeSub(int sqlDateTimeSub) {
        this.sqlDateTimeSub = sqlDateTimeSub;
    }

    @XmlTransient
    public int getCharOctetLength() {
        return charOctetLength;
    }

    public void setCharOctetLength(int charOctetLength) {
        this.charOctetLength = charOctetLength;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }
}
