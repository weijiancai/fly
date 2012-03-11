package com.fly.sys.module;

import java.util.List;

/**
 * 系统模块管理
 *
 * @author weijiancai
 */
public class SysModule implements Module {
    // 主键
    private String id;
    // 模块名称
    private String name;
    // 父模块
    private Module superModule;
    // 子模块列表
    private List<Module> childModules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getSuperModule() {
        return superModule;
    }

    public void setSuperModule(Module superModule) {
        this.superModule = superModule;
    }

    public List<Module> getChildModules() {
        return childModules;
    }

    public void setChildModules(List<Module> childModules) {
        this.childModules = childModules;
    }
}
