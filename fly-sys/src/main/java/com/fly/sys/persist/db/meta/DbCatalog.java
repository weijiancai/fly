package com.fly.sys.persist.db.meta;

import com.fly.sys.persist.xml.MapAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
public class DbCatalog {
    private String name;
    private List<DbProcedure> procedures;
    private List<DbTable> tables;
    private Map<String, List<DbTable>> tableMap;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "Procedures")
    @XmlElement(name = "Procedure")
    public List<DbProcedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<DbProcedure> procedures) {
        this.procedures = procedures;
    }

    @XmlElementWrapper(name = "Tables")
    @XmlElement(name = "Table")
    public List<DbTable> getTables() {
        return tables;
    }

    public void setTables(List<DbTable> tables) {
        this.tables = tables;
    }

    @XmlTransient
    public Map<String, List<DbTable>> getTableMap() {
        return tableMap;
    }

    public void setTableMap(Map<String, List<DbTable>> tableMap) {
        this.tableMap = tableMap;
    }
}
