package com.fly.test.fix;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author weijiancai
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Addresss {
    @XmlAttribute
    private String country;
    @XmlElement
    private String state;
    @XmlElement
    private String city;
    @XmlElement
    private String street;
    private String zipcode; //由于没有添加@XmlElement,所以该元素不会出现在输出的xml中

    public Addresss() {
    }

    public Addresss(String country, String state, String city, String street, String zipcode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
