package com.fly.sys.persist;

import com.fly.sys.persist.xml.JAXBDao;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DaoFactory {
    public static JAXBDao getJAXBDao(String filePath) {
        return new JAXBDao(filePath);
    }
}
