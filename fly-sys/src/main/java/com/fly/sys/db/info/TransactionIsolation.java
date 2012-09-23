package com.fly.sys.db.info;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public enum TransactionIsolation {
    TRANSACTION_NONE,
    TRANSACTION_READ_UNCOMMITTED,
    TRANSACTION_READ_COMMITTED,
    TRANSACTION_REPEATABLE_READ,
    TRANSACTION_SERIALIZABLE;

    public static TransactionIsolation getTransactionIsolation(int transactionIsolation) {
        switch (transactionIsolation) {
            case 0:
                return TransactionIsolation.TRANSACTION_NONE;
            case 1:
                return TransactionIsolation.TRANSACTION_READ_UNCOMMITTED;
            case 2:
                return TransactionIsolation.TRANSACTION_READ_COMMITTED;
            case 4:
                return TransactionIsolation.TRANSACTION_REPEATABLE_READ;
            case 8:
                return TransactionIsolation.TRANSACTION_SERIALIZABLE;
        }

        return null;
    }
}
