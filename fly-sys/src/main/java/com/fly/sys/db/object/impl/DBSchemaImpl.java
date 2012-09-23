package com.fly.sys.db.object.impl;

import com.fly.sys.db.object.*;

import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DBSchemaImpl implements DBSchema {
    private String name;
    private String comment;

    @Override
    public List<DBTable> getTables() {
        return null;
    }

    @Override
    public List<DBView> getViews() {
        return null;
    }

    @Override
    public List<DBProcedure> getProcedures() {
        return null;
    }

    @Override
    public List<DBFunction> getFunctions() {
        return null;
    }

    @Override
    public DBTable getTable(String name) {
        return null;
    }

    @Override
    public DBView getView(String name) {
        return null;
    }

    @Override
    public DBProcedure getProcedure(String name) {
        return null;
    }

    @Override
    public DBFunction getFunction(String name) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
