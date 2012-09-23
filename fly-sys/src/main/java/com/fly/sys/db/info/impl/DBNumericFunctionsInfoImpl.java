package com.fly.sys.db.info.impl;

import com.fly.sys.db.info.DBNumericFunctionsInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据库数字函数信息
 *
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement(name = "NumericFunctions")
public class DBNumericFunctionsInfoImpl implements DBNumericFunctionsInfo {
    private String[] numericFunctions;

    public DBNumericFunctionsInfoImpl() {}

    public DBNumericFunctionsInfoImpl(String numericFunctions) {
        this.numericFunctions = numericFunctions.split(",");
    }

    @XmlElement(name = "function")
    public String[] getNumericFunctions() {
        return numericFunctions;
    }
}
