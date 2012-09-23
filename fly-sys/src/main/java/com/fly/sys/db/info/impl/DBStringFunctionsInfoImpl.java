package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBStringFunctionsInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据库字符串函数信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "StringFunctions")
public class DBStringFunctionsInfoImpl implements DBStringFunctionsInfo {
    private String[] stringFunctions;

    public DBStringFunctionsInfoImpl() {}

    public DBStringFunctionsInfoImpl(String stringFunctions) {
        this.stringFunctions = stringFunctions.split(",");
    }

    @XmlElement(name = "function")
    public String[] getStringFunctions() {
        return stringFunctions;
    }
}
