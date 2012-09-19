package com.fly.sys.persist.xml;

import com.fly.sys.persist.DAO;
import com.fly.sys.persist.DaoFactory;
import com.fly.test.fix.Address;
import com.fly.test.fix.Person;
import com.fly.test.fix.PersonFactory;
import org.junit.Test;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class JAXBDaoTest {
    @Test
    public void testSave() throws Exception {
        Person person = PersonFactory.getPerson();
        JAXBDao dao = DaoFactory.getJAXBDao("c:/person.xml");
        dao.save(person);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}
