package com.fly.base.pm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 组结构规则联合主键
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Embeddable
public class GroupStructureRulePK implements Serializable {
    private GroupStructureType groupStructType;
    private GroupType groupType;
    private GroupType parentGroupType;

    public GroupStructureRulePK() {}

    public GroupStructureRulePK(int groupStructTypeId, int groupTypeId, int parentGroupTypeId) {
        this.groupStructType = new GroupStructureType();
        groupStructType.setId(groupStructTypeId);
        this.groupType = new GroupType();
        groupType.setId(groupTypeId);
        this.parentGroupType = new GroupType();
        parentGroupType.setId(parentGroupTypeId);
    }

    public GroupStructureRulePK(GroupStructureType groupStructType, GroupType groupType, GroupType parentGroupType) {
        this.groupStructType = groupStructType;
        this.groupType = groupType;
        this.parentGroupType = parentGroupType;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "struct_type_id")
    public GroupStructureType getGroupStructType() {
        return groupStructType;
    }

    public void setGroupStructType(GroupStructureType groupStructType) {
        this.groupStructType = groupStructType;
    }

    @ManyToOne
    @JoinColumn(name = "group_type_id")
    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    @ManyToOne
    @JoinColumn(name = "group_type_pid")
    public GroupType getParentGroupType() {
        return parentGroupType;
    }

    public void setParentGroupType(GroupType parentGroupType) {
        this.parentGroupType = parentGroupType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupStructureRulePK that = (GroupStructureRulePK) o;

        if (groupStructType != null ? !groupStructType.equals(that.groupStructType) : that.groupStructType != null)
            return false;
        if (groupType != null ? !groupType.equals(that.groupType) : that.groupType != null) return false;
        if (parentGroupType != null ? !parentGroupType.equals(that.parentGroupType) : that.parentGroupType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupStructType != null ? groupStructType.hashCode() : 0;
        result = 31 * result + (groupType != null ? groupType.hashCode() : 0);
        result = 31 * result + (parentGroupType != null ? parentGroupType.hashCode() : 0);
        return result;
    }
}
