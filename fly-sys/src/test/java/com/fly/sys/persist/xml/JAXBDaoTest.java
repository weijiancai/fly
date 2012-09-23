package com.fly.sys.persist.xml;

import com.fly.sys.persist.DAO;
import com.fly.sys.persist.DaoFactory;
import com.fly.sys.project.ProjectDefine;
import com.fly.test.fix.*;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class JAXBDaoTest {
    @Test
    public void testSave() throws Exception {
        Person person = PersonFactory.getPerson();
        JAXBDao<Person> dao = DaoFactory.getJAXBDao("person.xml", Person.class);
        dao.save(person);

        /*Addresss address = new Addresss("China","Beijing","Beijing","ShangDi West","100080");
        Persons p = new Persons(Calendar.getInstance(),"JAXB2",address, Gender.MALE,"SW");
        JAXBDao<Persons> dao = DaoFactory.getJAXBDao("person.xml");
        dao.save(p);*/

        ProjectDefine project = new ProjectDefine();
        project.setId("project_1");
        project.setName("SYS_framework");
        project.setProjectDesc("FLY 系统框架");
        project.setInputDate(new Date());
        project.setProjectUrl("http://localhost:8080");
        project.setSortNum(10);
        project.setValid(true);
        DAO<ProjectDefine> projectDefineDAO = DaoFactory.getJAXBDao("ProjectDefine.xml", ProjectDefine.class);
        projectDefineDAO.save(project);
    }

    @Test
    public void testXmlDataStore() throws JAXBException {
        JAXBDao<Person> dao = DaoFactory.getJAXBDao("person.xml", Person.class);
        System.out.println(dao.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        XmlDataStore<Persons> xmlDataStore = new XmlDataStore<Persons>();
        xmlDataStore.setName(Persons.class.getName());

        JAXBContext context = JAXBContext.newInstance(XmlDataStore.class, Persons.class);
        //下面代码演示将对象转变为xml
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Addresss address = new Addresss("China","Beijing","Beijing","ShangDi West","100080");
        Persons p = new Persons(Calendar.getInstance(),"JAXB2",address, Gender.MALE,"SW");

        xmlDataStore.add(p);
        xmlDataStore.add(p);

        FileWriter fw = new FileWriter("person.xml");
        m.marshal(xmlDataStore,fw);

        //下面代码演示将上面生成的xml转换为对象
        FileReader fr = new FileReader("person.xml");
        Unmarshaller um = context.createUnmarshaller();
//        Persons p2 = (Persons)um.unmarshal(fr);
        XmlDataStore<Persons> xmlDataStore1 = (XmlDataStore<Persons>) um.unmarshal(fr);
//        System.out.println("Country:"+p2.getAddress().getCountry());
        System.out.println(xmlDataStore1.getList().size());
    }

    @Test
    public void testGetAll() throws Exception {

    }
}
