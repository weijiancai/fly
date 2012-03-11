package com.fly.sys.module;

import java.util.List;

/**
 * @author weijiancai
 */
public interface Module {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    Module getSuperModule();

    void setSuperModule(Module superModule);

    List<Module> getChildModules();

    void setChildModules(List<Module> childModules);
}
