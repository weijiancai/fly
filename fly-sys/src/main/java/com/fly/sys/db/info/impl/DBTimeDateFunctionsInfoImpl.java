package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBTimeDateFunctionsInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据库时间、日期函数信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "TimeDateFunctions")
public class DBTimeDateFunctionsInfoImpl implements DBTimeDateFunctionsInfo {
    private String[] timeDateFunctions;

    public DBTimeDateFunctionsInfoImpl() {}

    public DBTimeDateFunctionsInfoImpl(String timeDateFunctions) {
        this.timeDateFunctions = timeDateFunctions.split(",");
    }

    @XmlElement(name = "function")
    public String[] getTimeDateFunctions() {
        return timeDateFunctions;
    }
}
