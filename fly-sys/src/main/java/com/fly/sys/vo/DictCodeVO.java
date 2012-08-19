package com.fly.sys.vo;

import com.fly.sys.dict.DictCode;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DictCodeVO {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static DictCodeVO getInstance(DictCode code) {
        DictCodeVO vo = new DictCodeVO();
        vo.setName(code.getName());
        vo.setValue(code.getValue());

        return vo;
    }
}
