package com.fly.base.pm.entity;

import javax.persistence.*;

/**
 * 组结构类型实体
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "sys_pm_group_structure_type")
public class GroupStructureType {
    private Integer id;
    private String name;

    public GroupStructureType() {}

    public GroupStructureType(String name) {
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

        GroupStructureType that = (GroupStructureType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
