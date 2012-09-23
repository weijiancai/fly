package com.fly.sys.persist.db.meta;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public enum ProcedureColumnType {
    UNKNOWN,  // procedureColumnUnknown
    IN,  // procedureColumnIn
    INOUT,  // procedureColumnInOut
    OUT,  // procedureColumnOut
    RETURN,  // procedureColumnReturn
    RESULT  // procedureColumnResult
    ;


    public static ProcedureColumnType getColumnType(int type) {
        switch (type) {
            case 0:
                return UNKNOWN;
            case 1:
                return IN;
            case 2:
                return INOUT;
            case 4:
                return OUT;
            case 5:
                return RETURN;
            case 3:
                return RESULT;
        }

        return null;
    }
}
