package com.fly.base.ui.component.appmenu;

import java.util.List;

/**
 * 应用程序菜单
 *
 * @author weijiancai
 * @since 0.0.1
 */
public interface IAppMenu {
    /**
     * 获取菜单名称
     *
     * @return 返回菜单名称
     * @since 0.0.1
     */
    String getMenuName();

    /**
     * 获取此菜单下的菜单项列表
     *
     * @return 返回此菜单下的菜单项列表
     * @since 0.0.1
     */
    List<IAppMenuItem> getMenuItems();
}
