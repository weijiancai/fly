package com.fly.base.pm.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 组，团体是人和组织的抽象概念
 *
 * @author weijiancai
 * @version 0.0.1
 */
@XmlRootElement
@Entity
@Table(name = "sys_pm_group")
public class Group {
    private Long id;
    private String name;
    private GroupType groupType;

    public Group() {}

    public Group(String name, GroupType groupType) {
        this.name = name;
        this.groupType = groupType;
    }

    @Id @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 126, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_type")
    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
