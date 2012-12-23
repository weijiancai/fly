package com.fly.base.pm.entity;

import org.hibernate.type.ManyToOneType;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 组结构联合主键
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Embeddable
public class GroupStructurePK implements Serializable {
    private GroupStructureType groupStructType;
    private Group group;
    private Group parentGroup;

    public GroupStructurePK() {}

    public GroupStructurePK(int groupStructTypeId, long groupId, long parentGroupId) {
        this.groupStructType = new GroupStructureType();
        groupStructType.setId(groupStructTypeId);
        this.group = new Group();
        group.setId(groupId);
        this.parentGroup = new Group();
        parentGroup.setId(parentGroupId);
    }

    @ManyToOne
    @JoinColumn(name = "struct_type_id")
    public GroupStructureType getGroupStructType() {
        return groupStructType;
    }

    public void setGroupStructType(GroupStructureType groupStructType) {
        this.groupStructType = groupStructType;
    }

    @ManyToOne
    @JoinColumn(name = "group_id")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name = "group_pid")
    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupStructurePK that = (GroupStructurePK) o;

        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (groupStructType != null ? !groupStructType.equals(that.groupStructType) : that.groupStructType != null)
            return false;
        if (parentGroup != null ? !parentGroup.equals(that.parentGroup) : that.parentGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupStructType != null ? groupStructType.hashCode() : 0;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (parentGroup != null ? parentGroup.hashCode() : 0);
        return result;
    }
}
