package com.fly.sys.db.info;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public enum Holdability {
    HOLD_CURSORS_OVER_COMMIT,
    CLOSE_CURSORS_AT_COMMIT
    ;

    public static Holdability getHoldability(int holdability) {
        switch (holdability) {
            case 1:
                return Holdability.HOLD_CURSORS_OVER_COMMIT;
            case 2:
                return Holdability.CLOSE_CURSORS_AT_COMMIT;
        }

        return null;
    }
}
