package com.fly.base.ui.component.menu.impl;

import com.fly.base.ui.component.menu.IAppMenu;
import com.fly.base.ui.component.menu.IAppMenuItem;
import com.fly.base.ui.component.menu.IAppMenuPane;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.List;

/**
 * AppMenuPane 的树形菜单面板实现
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class TreeAppMenuPane implements IAppMenuPane {
    private TreeView<String> root;
    private TreeItem<String> rootItem;

    public TreeAppMenuPane() {
        root = new TreeView<String>();
        rootItem = new TreeItem<String>("Root");
        root.setRoot(rootItem);
        root.setShowRoot(false);
    }

    @Override
    public void setAppMenus(List<IAppMenu> menus) {
        for (IAppMenu menu : menus) {
            TreeItem<String> treeItem = new TreeItem<String>(menu.getName());
            rootItem.getChildren().add(treeItem);
            List<IAppMenuItem> items = menu.getMenuItems();
            if (null != items && items.size() > 0) {
                for (IAppMenuItem item : items) {
                    treeItem.getChildren().add(new TreeItem<String>(item.getName()));
                }
            }
        }
    }

    @Override
    public Parent getMenuPane() {
        return root;
    }
}
