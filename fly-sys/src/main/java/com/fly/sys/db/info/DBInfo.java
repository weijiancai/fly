package com.fly.sys.db.info;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface DBInfo {
    String getURL();
    String getUserName();
    String getPassword();
    boolean isReadOnly();
    String getIdentifierQuoteString();
    String getSearchStringEscape();
    String getExtraNameCharacters();

    String getSqlKeywords();
}
