package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBConnectionInfo;
import com.fly.sys.db.info.Holdability;
import com.fly.sys.db.info.TransactionIsolation;
import com.fly.sys.persist.xml.MapAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库连接信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "DBConnectionInfo")
@XmlType(propOrder = {"autoCommit", "readOnly", "schema", "transactionIsolation", "holdability"})
public class DBConnectionInfoImpl implements DBConnectionInfo {
    private boolean isAutoCommit;
    private boolean isReadOnly;
    private String schema;
    private TransactionIsolation transactionIsolation;
    private Holdability holdability;

    public void setAutoCommit(boolean autoCommit) {
        isAutoCommit = autoCommit;
    }

    @XmlElement
    public boolean isAutoCommit() {
        return isAutoCommit;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }

    @XmlElement
    public boolean isReadOnly() {
        return isReadOnly;
    }

    @XmlElement
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @XmlElement
    public TransactionIsolation getTransactionIsolation() {
        return transactionIsolation;
    }

    public void setTransactionIsolation(int transactionIsolation) {
        this.transactionIsolation = TransactionIsolation.getTransactionIsolation(transactionIsolation);
    }

    @XmlElement
    public Holdability getHoldability() {
        return holdability;
    }

    public void setHoldability(int holdability) {
        this.holdability = Holdability.getHoldability(holdability);
    }
}
