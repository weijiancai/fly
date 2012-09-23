package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBNullInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 数据库NULL值处理信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "DBNullInfo")
public class DBNullInfoImpl implements DBNullInfo {
    private DatabaseMetaData meta;

    public DBNullInfoImpl() {}

    public DBNullInfoImpl(DatabaseMetaData meta) {
        this.meta =meta;
    }

    @XmlElement
    public boolean getNullsAreSortedHigh() throws SQLException {
        return meta.nullsAreSortedHigh();
    }

    @XmlElement
    public boolean getNullsAreSortedLow() throws SQLException {
        return meta.nullsAreSortedLow();
    }

    @XmlElement
    public boolean getNullsAreSortedAtStart() throws SQLException {
        return meta.nullsAreSortedAtStart();
    }

    @XmlElement
    public boolean getNullsAreSortedAtEnd() throws SQLException {
        return meta.nullsAreSortedAtEnd();
    }

    @XmlElement
    public boolean getNullPlusNonNullIsNull() throws SQLException {
        return meta.nullPlusNonNullIsNull();
    }
}
