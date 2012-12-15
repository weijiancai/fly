package com.fly.base.ui;

import com.fly.base.ui.component.appframe.impl.BaseAppFrame;
import com.fly.base.ui.component.appframe.IAppFrame;
import com.fly.base.ui.component.menu.*;
import com.fly.base.ui.component.menu.impl.AccordionAppMenuPane;
import com.fly.base.ui.component.menu.impl.BaseAppMenu;
import com.fly.base.ui.component.menu.impl.BaseAppMenuItem;
import com.fly.base.ui.component.menu.impl.TreeAppMenuPane;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * 主应用程序
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class BaseApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("FLY 基础库");
        Group root = new Group();

        IAppFrame frame = new BaseAppFrame();
        IAppMenuPane menuPane = new TreeAppMenuPane();
        List<IAppMenu> menus = new ArrayList<IAppMenu>();
        IAppMenu menu1 = new BaseAppMenu("测试菜单1");
        List<IAppMenuItem> items = new ArrayList<IAppMenuItem>();
        items.add(new BaseAppMenuItem("测试菜单1-1"));
        items.add(new BaseAppMenuItem("测试菜单1-2"));
        items.add(new BaseAppMenuItem("测试菜单1-3"));
        items.add(new BaseAppMenuItem("测试菜单1-4"));
        items.add(new BaseAppMenuItem("测试菜单1-5"));
        menu1.setMenuItems(items);
        menus.add(menu1);
        menus.add(new BaseAppMenu("测试菜单2"));
        menus.add(new BaseAppMenu("测试菜单3"));
        menus.add(new BaseAppMenu("测试菜单4"));
        menuPane.setAppMenus(menus);
        frame.setAppMenuPane(menuPane);

        Scene scene = new Scene(root, 900, 650);
        root.getChildren().add(frame.getAppFramePane());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
