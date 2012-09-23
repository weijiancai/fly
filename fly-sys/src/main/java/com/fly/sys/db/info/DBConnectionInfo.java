package com.fly.sys.db.info;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface DBConnectionInfo {
    boolean isAutoCommit();
    boolean isReadOnly();
    String getSchema();
    TransactionIsolation getTransactionIsolation();
    Holdability getHoldability();
}
