package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBSystemFunctionsInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据库系统函数信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "SystemFunctions")
public class DBSystemFunctionsInfoImpl implements DBSystemFunctionsInfo {
    private String[] systemFunctions;

    public DBSystemFunctionsInfoImpl() {}

    public DBSystemFunctionsInfoImpl(String systemFunctions) {
        this.systemFunctions = systemFunctions.split(",");
    }

    @XmlElement(name = "function")
    public String[] getSystemFunctions() {
        return systemFunctions;
    }
}
