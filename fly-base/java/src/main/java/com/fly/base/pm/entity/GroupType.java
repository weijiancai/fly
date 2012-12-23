package com.fly.base.pm.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 组类型实体
 *
 * @author weijiancai
 * @version 0.0.1
 */
@XmlRootElement
@Entity
@Table(name = "sys_pm_group_type")
public class GroupType {
    private Integer id;
    private String name;

    public GroupType() {}

    public GroupType(String name) {
        this.name = name;
    }

    @Id @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 126, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupType groupType = (GroupType) o;

        if (id != null ? !id.equals(groupType.id) : groupType.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
