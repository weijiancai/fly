package com.fly.sys.persist.xml;

import com.fly.sys.persist.DAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class JAXBDao<T> implements DAO<T> {
    private String filePath;
    private List<Object> list = new ArrayList<Object>();


    public JAXBDao(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Object obj) throws Exception {
//        list.add(obj);
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        XmlDataStore list = new XmlDataStore();
        list.add(obj);
//        list.setFilePath(filePath);
        marshaller.marshal(obj, new File(filePath));
    }

    public void save(Object obj, Class<?>... classes) throws Exception {
        JAXBContext context = JAXBContext.newInstance(classes);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        XmlList list = new XmlList();
        list.add(obj);
        list.setFilePath(filePath);
        marshaller.marshal(list, new File(filePath));
    }

    @Override
    public void update(Object obj) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll() {
        return (List<T>) list;
    }
}
