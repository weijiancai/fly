package com.fly.base.pm.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 组结构规则
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "sys_pm_group_structure_rule")
public class GroupStructureRule {
    private GroupStructureRulePK id;

    public GroupStructureRule() {}

    public GroupStructureRule(GroupStructureRulePK id) {
        this.id = id;
    }

    @EmbeddedId
    public GroupStructureRulePK getId() {
        return id;
    }

    public void setId(GroupStructureRulePK id) {
        this.id = id;
    }
}
