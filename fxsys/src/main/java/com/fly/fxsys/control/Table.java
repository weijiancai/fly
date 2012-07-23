package com.fly.fxsys.control;

import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.util.Map;

/**
 * 表格视图，包括查询条件面板、表单面板，工具条，table面板
 * @author weijiancai
 */
public class Table extends StackPane {
    private TablePane tablePane;

    public TablePane getTablePane() {
        return tablePane;
    }

    public void setTableView(com.fly.sys.view.table.TableView tableView) {
        tablePane = new TablePane();
        tablePane.setTableView(tableView);
        this.getChildren().add(tablePane);
    }
}
