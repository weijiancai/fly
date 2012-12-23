package com.fly.base.persist.xml;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weijiancai
 * @since 1.0.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDataStore<T> {
    private String name;
    /*@XmlElements(
            @XmlElement(name = "ProjectDefine", type = ProjectDefine.class)
    )*/
    @XmlElementWrapper
    @XmlAnyElement
    private Set<T> list = new HashSet<T>();

    public Set<T> getList() {
        return list;
    }

    public void setList(Set<T> list) {
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
