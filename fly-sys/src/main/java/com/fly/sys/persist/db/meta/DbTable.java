package com.fly.sys.persist.db.meta;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据库表
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "Table")
public class DbTable {
    private String name;
    private TableType tableType;
    private String remarks;
    private String typeCatalog;
    private String typeSchema;
    private String typeName;
    private String selfReferencingColName;
    private String refGeneration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTypeCatalog() {
        return typeCatalog;
    }

    public void setTypeCatalog(String typeCatalog) {
        this.typeCatalog = typeCatalog;
    }

    public String getTypeSchema() {
        return typeSchema;
    }

    public void setTypeSchema(String typeSchema) {
        this.typeSchema = typeSchema;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSelfReferencingColName() {
        return selfReferencingColName;
    }

    public void setSelfReferencingColName(String selfReferencingColName) {
        this.selfReferencingColName = selfReferencingColName;
    }

    public String getRefGeneration() {
        return refGeneration;
    }

    public void setRefGeneration(String refGeneration) {
        this.refGeneration = refGeneration;
    }
}
