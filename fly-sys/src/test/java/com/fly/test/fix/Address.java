package com.fly.test.fix;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement
    private String city;
    @XmlElement
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
