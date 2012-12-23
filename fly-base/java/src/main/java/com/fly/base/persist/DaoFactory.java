package com.fly.base.persist;

import com.fly.base.persist.xml.JAXBDao;
import javax.xml.bind.JAXBException;

/**
 * 数据访问工厂
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class DaoFactory {
    public static <T> JAXBDao<T> getJAXBDao(String filePath, Class<T> clazz) throws JAXBException {
        return new JAXBDao<T>(filePath, clazz);
    }
}
