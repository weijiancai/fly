package com.meteorite.dbtools.idea.editor;

import com.meteorite.dbtools.idea.common.util.EnumerationUtil;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum DBContentType {
    NONE("No Content"),
    DATA("Data"),

    CODE("Code"),
    CODE_SPEC("Code Spec"),
    CODE_BODY("Code Body", "BODY"),
    CODE_SPEC_AND_BODY("Code Spec and Body", new DBContentType[]{CODE_SPEC, CODE_BODY}),
    CODE_AND_DATA("Code and Data", new DBContentType[]{CODE, DATA});

    private DBContentType[] subContentTypes = new DBContentType[0];
    private String description;
    private String objectTypeSubname;

    private DBContentType(String description, DBContentType[] subContentTypes) {
        this.description = description;
        this.subContentTypes = subContentTypes;
    }

    private DBContentType(String description) {
        this.description = description;
    }

    DBContentType(String description, String objectTypeSubname) {
        this.description = description;
        this.objectTypeSubname = objectTypeSubname;
    }

    public DBContentType[] getSubContentTypes() {
        return subContentTypes;
    }

    public boolean isBundle() {
        return subContentTypes.length > 0;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isCode() {
        return this == CODE || this == CODE_SPEC || this == CODE_BODY || this == CODE_SPEC_AND_BODY;
    }

    public boolean isData() {
        return this == DATA;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }

    public String getObjectTypeSubname() {
        return objectTypeSubname;
    }

    public boolean isOneOf(DBContentType ... contentTypes){
        return EnumerationUtil.isOneOf(this, contentTypes);
    }
}
