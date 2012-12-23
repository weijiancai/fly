package com.fly.base.pm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
}
