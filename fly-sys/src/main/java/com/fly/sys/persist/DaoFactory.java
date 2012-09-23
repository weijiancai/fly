package com.fly.sys.persist;

import com.fly.sys.persist.xml.JAXBDao;

import javax.xml.bind.JAXBException;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DaoFactory {
    public static <T> JAXBDao<T> getJAXBDao(String filePath, Class<T> clazz) throws JAXBException {
        return new JAXBDao<T>(filePath, clazz);
    }
}
