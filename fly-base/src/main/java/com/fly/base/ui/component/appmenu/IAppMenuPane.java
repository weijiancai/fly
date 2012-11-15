package com.fly.base.ui.component.appmenu;

import java.util.List;

/**
 * 应用程序菜单面板
 *
 * @author weijiancai
 * @since 0.0.1
 */
public interface IAppMenuPane {
    /**
     * 设置应用程序菜单
     *
     * @param menus 菜单列表
     * @since 0.0.1
     */
    void setAppMenus(List<IAppMenu> menus);
}
