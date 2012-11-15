package com.fly.base.ui.component.appframe;

import com.fly.base.ui.component.appmenu.IAppMenuPane;

/**
 * 应用程序主窗口
 *
 * @author weijiancai
 * @since 0.0.1
 */
public interface IAppFrame {
    /**
     * 获得应用程序菜单面板
     *
     * @return 返回应用程序菜单面板
     * @since 0.0.1
     */
    IAppMenuPane getAppMenuPane();
}
