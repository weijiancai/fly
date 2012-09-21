package com.fly.test.fix;

import javax.xml.bind.annotation.*;
import java.util.Calendar;

/**
 * @author weijiancai
 * @since 1.0.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {
    @XmlElement
    private Calendar birthDay; //birthday将作为person的子元素

    @XmlAttribute
    private String name; //name将作为person的的一个属性

    public Addresss getAddress() {
        return address;
    }

    @XmlElement
    private Addresss address; //address将作为person的子元素

    @XmlElement
    Gender gender; //gender将作为person的子元素

    @XmlElement
    String job; //job将作为person的子元素

    public Persons(){
    }

    public Persons(Calendar birthDay, String name, Addresss address, Gender gender, String job) {
        this.birthDay = birthDay;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.job = job;
    }

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
