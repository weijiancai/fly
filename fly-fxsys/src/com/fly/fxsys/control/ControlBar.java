package com.fly.fxsys.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ControlBar extends ToolBar {
    private Button btn_close;
    private Button btn_query;
    private Button btn_save;

    public ControlBar() {
        super();
        initToolBar();
    }

    private void initToolBar() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        this.getItems().add(region);

        btn_close = new Button("关闭");
        btn_query = new Button("查询");
        btn_save = new Button("保存");

        this.getItems().add(btn_query);
        this.getItems().add(btn_save);
        this.getItems().add(btn_close);
    }

    public void setCloseHandler(EventHandler<ActionEvent> closeHandler) {
        btn_close.setOnAction(closeHandler);
    }

    public void setQueryHandler(EventHandler<ActionEvent> queryHandler) {
        btn_query.setOnAction(queryHandler);
    }

    public void setSaveHandler(EventHandler<ActionEvent> saveHandler) {
        btn_save.setOnAction(saveHandler);
    }
}
