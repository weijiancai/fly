package com.fly.test.fix;

import java.util.Date;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class PersonFactory {
    public static Person getPerson() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(24);
        Date date = new Date();
        person.setBirthDay(date);
        person.setHeight(175);
        Address address = new Address();
        address.setCity("北京");
        address.setCode("010");
        person.setAddress(address);

        return person;
    }
}
