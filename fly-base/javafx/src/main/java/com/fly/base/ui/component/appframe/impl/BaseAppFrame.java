package com.fly.base.ui.component.appframe.impl;

import com.fly.base.ui.component.appframe.IAppFrame;
import com.fly.base.ui.component.menu.IAppMenuPane;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * AppFrame 的基本实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class BaseAppFrame implements IAppFrame {
    private BorderPane root;
    private IAppMenuPane menuPane;

    public BaseAppFrame() {
        root = new BorderPane();
    }

    @Override
    public IAppMenuPane getAppMenuPane() {
        return menuPane;
    }

    @Override
    public void setAppMenuPane(IAppMenuPane menuPane) {
        this.menuPane = menuPane;
        root.setLeft(menuPane.getMenuPane());
    }

    @Override
    public Parent getAppFramePane() {
        return root;
    }
}
