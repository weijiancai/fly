package com.fly.base.persist.xml;


import com.fly.base.persist.DAO;
import com.fly.base.util.JAXBUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Set;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class JAXBDao<T> implements DAO<T> {
    private XmlDataStore<T> xmlDataStore;
    private String filePath;
    private Class<T> clazz;

    public JAXBDao(String filePath, Class<T> clazz) throws JAXBException {
        this.filePath = filePath;
        this.clazz = clazz;

        load();
    }

    @SuppressWarnings("unchecked")
    private void load() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XmlDataStore.class, clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(filePath);
        if (file.exists()) {
            xmlDataStore = (XmlDataStore<T>) unmarshaller.unmarshal(file);
        } else {
            xmlDataStore = new XmlDataStore<T>();
        }
    }

    @Override
    public void save(T obj) throws Exception {
        xmlDataStore.add(obj);
        JAXBUtil.marshal(xmlDataStore, new File(filePath), XmlDataStore.class, obj.getClass());
    }

    @Override
    public void delete(T obj) throws Exception {
        if (getAll().contains(obj)) {
            getAll().remove(obj);
        }
        JAXBUtil.marshal(xmlDataStore, new File(filePath), XmlDataStore.class, obj.getClass());
    }

    @Override
    public void update(T obj) throws Exception {
        if (getAll().contains(obj)) {
            getAll().remove(obj);
            getAll().add(obj);
        }
        JAXBUtil.marshal(xmlDataStore, new File(filePath), XmlDataStore.class, obj.getClass());
    }

    @Override
    public Set<T> getAll() {
        return xmlDataStore.getList();
    }

    @Override
    public void persist() throws Exception {
    }
}
