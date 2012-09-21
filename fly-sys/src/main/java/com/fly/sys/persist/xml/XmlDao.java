package com.fly.sys.persist.xml;

import com.fly.sys.persist.DAO;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class XmlDao<T> implements DAO<T> {
    private Document doc;

    public XmlDao(String filePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        doc = builder.parse(new FileInputStream(filePath));
    }

    @Override
    public void save(Object obj) {

    }

    @Override
    public void update(Object obj) {
    }

    @Override
    public <T> List<T> getAll() {
        return null;
    }
}
