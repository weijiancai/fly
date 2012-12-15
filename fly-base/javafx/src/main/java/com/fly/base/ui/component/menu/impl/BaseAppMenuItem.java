package com.fly.base.ui.component.menu.impl;

import com.fly.base.ui.component.menu.IAppMenuItem;

/**
 * AppMenuItem 的基本实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class BaseAppMenuItem implements IAppMenuItem {
    private String name;

    public BaseAppMenuItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
