package com.fly.test.fix;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
public class Address {
    private String city;
    private String code;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
