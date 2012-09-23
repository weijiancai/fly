package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBSqlKeywordsInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据库SQL关键字信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "SQLKeywords")
public class DBSqlKeywordsInfoImpl implements DBSqlKeywordsInfo {
    private String[] keywords;

    public DBSqlKeywordsInfoImpl() {}

    public DBSqlKeywordsInfoImpl(String keywords) {
        this.keywords = keywords.split(",");
    }

    @XmlElement(name = "keyword")
    public String[] getKeywords() {
        return keywords;
    }
}
