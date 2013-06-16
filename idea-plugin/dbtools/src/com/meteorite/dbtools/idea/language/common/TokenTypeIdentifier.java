package com.meteorite.dbtools.idea.language.common;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum TokenTypeIdentifier {
    UNKNOWN("unknown"),
    KEYWORD("keyword"),
    FUNCTION("function"),
    PARAMETER("parameter"),
    DATATYPE("datatype"),
    EXCEPTION("exception"),
    OPERATOR("operator"),
    CHARACTER("character"),
    IDENTIFIER("identifier"),
    CHAMELEON("chameleon")
    ;

    private String name;

    TokenTypeIdentifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TokenTypeIdentifier getIdentifier(String name) {
        for (TokenTypeIdentifier identifier : TokenTypeIdentifier.values()) {
            if (identifier.name.equals(name)) {
                return identifier;
            }
        }

        return UNKNOWN;
    }
}
