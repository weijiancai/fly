package com.fly.base.ui.component.menu.impl;

import com.fly.base.ui.component.menu.IAppMenu;
import com.fly.base.ui.component.menu.IAppMenuItem;
import com.fly.base.ui.component.menu.IAppMenuPane;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * AppMenuPan 的Accordion基本实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class AccordionAppMenuPane implements IAppMenuPane {
    private Accordion root;

    public AccordionAppMenuPane() {
        root = new Accordion();
    }

    @Override
    public void setAppMenus(List<IAppMenu> menus) {
        for (IAppMenu menu : menus) {
            List<IAppMenuItem> items = menu.getMenuItems();
            VBox vBox = null;
            if (null != items && items.size() > 0) {
                vBox = new VBox();
                for (IAppMenuItem item : items) {
                    vBox.getChildren().add(new Label(item.getName()));
                }
            }
            root.getPanes().add(new TitledPane(menu.getName(), vBox));
        }
    }

    @Override
    public Parent getMenuPane() {
        return root;
    }
}
