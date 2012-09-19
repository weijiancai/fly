package com.fly.sys.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class UXmlTest {
    @Test
    public void testObjectToXmlString() throws Exception {
        Person person = getPerson();
        String birthDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(person.getBirthDay());
        String resultXml = UXml.toXmlString(person);
        String expectedXml = "<Person><name>张三</name><age>24</age><height>175.0</height><birthDay>" + birthDay + "</birthDay><isStudent>false</isStudent><address><city>北京</city><code>010</code></address></Person>";
        assertThat(resultXml, equalTo(expectedXml));
    }

    private Person getPerson() {
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

    @Test
    public void testMapToXmlString() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionId", "12345");
        map.put("uuid", 34234232);
        map.put("person", getPerson());
        map.put("isMap", true);

        System.out.println(UXml.toXmlString(map));
    }

    @Test
    public void testPrimitiveTypeObject() throws Exception {
        int a = 1;
        System.out.println(UXml.toXmlString(a));
        String str = "abcd";
        System.out.println(UXml.toXmlString(str));
    }

    @Test
    public void testList() throws Exception {
        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add(true);
        list.add(getPerson());
        list.add(new Date());
        list.add("张三");
        System.out.println(UXml.toXmlString(list));
    }

    @Test
    public void testToObject() throws Exception {
        Person person = getPerson();
        String xmlStr = UXml.toXmlString(person);
        Person result = UXml.toObject(xmlStr, Person.class);
        assertNotNull(result);
        assertThat(result.getName(), equalTo(person.getName()));
        assertThat(result.getAge(), equalTo(person.getAge()));
        assertThat(result.getBirthDay(), equalTo(person.getBirthDay()));
        assertThat(result.isStudent(), equalTo(person.isStudent()));
        assertThat(result.getAddress().getCity(), equalTo(person.getAddress().getCity()));
        assertThat(result.getAddress().getCode(), equalTo(person.getAddress().getCode()));
    }

    @Test
    public void testToIntegerObject() throws Exception {
        String xmlStr = "<Integer>5</Integer>";
        Integer result = UXml.toObject(xmlStr, Integer.class);
        assertThat(result, equalTo(5));
    }

    @Test
    public void testToLongObject() throws Exception {
        String xmlStr = "<Long>5</Long>";
        Long result = UXml.toObject(xmlStr, Long.class);
        assertThat(result, equalTo(5l));
    }

    @Test
    public void testToFloatObject() throws Exception {
        String xmlStr = "<Float>5.23</Float>";
        Float result = UXml.toObject(xmlStr, Float.class);
        assertThat(result, equalTo(5.23f));
    }

    @Test
    public void testToDoubleObject() throws Exception {
        String xmlStr = "<Double>5.23</Double>";
        Double result = UXml.toObject(xmlStr, Double.class);
        assertThat(result, equalTo(5.23));
    }

    @Test
    public void testToCharacterObject() throws Exception {
        String xmlStr = "<Character>a</Character>";
        Character result = UXml.toObject(xmlStr, Character.class);
        assertThat(result, equalTo('a'));
    }

    @Test
    public void testToBooleanObject() throws Exception {
        String xmlStr = "<Boolean>true</Boolean>";
        Boolean result = UXml.toObject(xmlStr, Boolean.class);
        assertThat(result, equalTo(true));
    }

    @Test
    public void testToStringObject() throws Exception {
        String xmlStr = "<String>abcd</String>";
        String result = UXml.toObject(xmlStr, String.class);
        assertThat(result, equalTo("abcd"));
    }

    @Test
    public void testToDateObject() throws Exception {
        Date date = new Date();
        String xmlStr = "<Date>" + date.getTime() + "</Date>";
        Date result = UXml.toObject(xmlStr, Date.class);
        assertThat(result, notNullValue());
        assertThat(result.getTime(), equalTo(date.getTime()));
    }
}

class Person {
    private String name;
    private int age;
    private double height;
    private Date birthDay;
    private boolean isStudent;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address {
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
