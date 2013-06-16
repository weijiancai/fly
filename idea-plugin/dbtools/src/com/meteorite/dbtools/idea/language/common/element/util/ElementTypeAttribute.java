package com.meteorite.dbtools.idea.language.common.element.util;

import gnu.trove.THashSet;

import java.util.Set;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum ElementTypeAttribute {
    ROOT("ROOT", "Executable statement"),
    EXECUTABLE("EXECUTABLE", "Executable statement"),
    TRANSACTIONAL("TRANSACTIONAL", "Transactional statement"),
    QUERY("QUERY", "Query statement"),
    DATA_DEFINITION("DATA_DEFINITION", "Data definition statement"),
    DATA_MANIPULATION("DATA_MANIPULATION", "Data manipulation statement"),
    TRANSACTION_CONTROL("TRANSACTION_CONTROL", "Transaction control statement"),
    OBJECT_SPECIFICATION("OBJECT_SPECIFICATION", "Object specification"),
    DECLARATION("DECLARATION", "Declaration"),
    OBJECT_DECLARATION("OBJECT_DECLARATION", "Object definition"),
    SUBJECT("SUBJECT", "Statement subject"),
    STATEMENT("STATEMENT", "Statement"),
    CLAUSE("CLAUSE", "Statement clause"),
    STRUCTURE("STRUCTURE", "Structure view element"),
    SCOPE_ISOLATION("SCOPE_ISOLATION", "Scope isolation"),
    SCOPE_DEMARCATION("SCOPE_DEMARCATION", "Scope demarcation"),
    FOLDABLE_BLOCK("FOLDABLE_BLOCK", "Foldable block"),
    DDL_STATEMENT("DDL_STATEMENT", "DDL statement"),
    EXECUTABLE_CODE("EXECUTABLE_CODE", "Executable code"),
    BREAKPOINT_POSITION("BREAKPOINT_POSITION", "Default breakpoint position")
    ;

    public static final Set<ElementTypeAttribute> EMPTY_LIST = new THashSet<ElementTypeAttribute>(0);

    private String name;
    private String description;

    ElementTypeAttribute(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
