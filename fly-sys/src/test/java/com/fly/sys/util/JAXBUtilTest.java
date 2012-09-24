package com.fly.sys.util;

import com.fly.test.fix.Person;
import com.fly.test.fix.PersonFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class JAXBUtilTest {
    @Test
    public void testMarshalMap() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "zhangsan");
        map.put("age", "24");

        Person person = PersonFactory.getPerson();
        person.setMap(map);

        String result = JAXBUtil.marshal(person);
        System.out.println(result);
    }
}
