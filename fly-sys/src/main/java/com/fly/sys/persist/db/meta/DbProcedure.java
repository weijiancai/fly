package com.fly.sys.persist.db.meta;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.Transient;
import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "Procedure")
public class DbProcedure {
    private String catalog;
    private String schema;
    private String name;
    private String remarks;
    private ProcedureType type;
    private String specificName;
    private List<ProcedureColumn> procedureColumns;

    @XmlTransient
    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    @XmlElement
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @XmlElement
    public ProcedureType getType() {
        return type;
    }

    public void setType(ProcedureType type) {
        this.type = type;
    }

    @XmlElement
    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    @XmlElementWrapper(name = "ProcedureColumns")
    @XmlElement(name = "ProcedureColumn")
    public List<ProcedureColumn> getProcedureColumns() {
        return procedureColumns;
    }

    public void setProcedureColumns(List<ProcedureColumn> procedureColumns) {
        this.procedureColumns = procedureColumns;
    }
}
