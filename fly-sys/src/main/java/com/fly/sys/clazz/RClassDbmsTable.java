package com.fly.sys.clazz;

import com.fly.sys.db.DBManager;
import com.fly.sys.db.meta.DbmsTable;

import java.io.Serializable;

/**
 * 类和数据库表的关联信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class RClassDbmsTable implements Serializable {
    private String classId;
    private String dbmsTableId;
    private boolean isPrimary;
    private String joinSql;

    private DbmsTable dbmsTable;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDbmsTableId() {
        return dbmsTableId;
    }

    public void setDbmsTableId(String dbmsTableId) {
        this.dbmsTableId = dbmsTableId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getJoinSql() {
        return joinSql;
    }

    public void setJoinSql(String joinSql) {
        this.joinSql = joinSql;
    }

    public DbmsTable getDbmsTable() {
        return DBManager.getDbTableById(dbmsTableId);
    }
}
