package com.fly.base.ui.component.menu.impl;

import com.fly.base.ui.component.menu.IAppMenu;
import com.fly.base.ui.component.menu.IAppMenuItem;

import java.util.List;

/**
 * AppMenu 的基本实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class BaseAppMenu implements IAppMenu {
    private String name;
    private List<IAppMenuItem> menuItems;

    public BaseAppMenu(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IAppMenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public void setMenuItems(List<IAppMenuItem> items) {
        this.menuItems = items;
    }
}
