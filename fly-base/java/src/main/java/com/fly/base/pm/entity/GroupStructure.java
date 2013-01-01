package com.fly.base.pm.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * 组结构实体
 *
 * @author weijiancai
 * @version 0.0.1
 */
@XmlRootElement
@Entity
@Table(name = "sys_pm_group_structure")
public class GroupStructure {
    private GroupStructurePK id;
    private Set<GroupStructure> children = new HashSet<GroupStructure>();

    public GroupStructure() {}

    public GroupStructure(GroupStructurePK id) {
        this.id = id;
    }

    @EmbeddedId
    public GroupStructurePK getId() {
        return id;
    }

    public void setId(GroupStructurePK id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    public Set<GroupStructure> getChildren() {
        return children;
    }

    public void setChildren(Set<GroupStructure> children) {
        this.children = children;
    }
}
