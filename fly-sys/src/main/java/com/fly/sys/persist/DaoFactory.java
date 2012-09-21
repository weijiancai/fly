package com.fly.sys.persist;

import com.fly.sys.persist.xml.JAXBDao;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DaoFactory {
    public static <T> JAXBDao getJAXBDao(String filePath, Class<T> clazz) {
        return new JAXBDao<T>(filePath);
    }
}
