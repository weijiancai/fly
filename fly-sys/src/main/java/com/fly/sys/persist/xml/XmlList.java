package com.fly.sys.persist.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@XmlRootElement
@XmlType(namespace = "xml.persist.sys.fly.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlList {
    @XmlElement
    String filePath;
    @XmlAnyElement
    private List<Object> list = new ArrayList<Object>();

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public void add(Object obj) {
        list.add(obj);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
