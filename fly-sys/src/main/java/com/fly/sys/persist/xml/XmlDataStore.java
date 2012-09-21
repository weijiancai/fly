package com.fly.sys.persist.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijiancai
 * @since 1.0.0
 */
@XmlRootElement
public class XmlDataStore<T> {
    private String name;
    private List<T> list = new ArrayList<T>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void add(T obj) {
        list.add(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
