package com.fly.fxsys.control;

import com.fly.sys.view.table.ColAttr;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.List;
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

class TablePane extends TableView<Map<String, Object>> {
    private com.fly.sys.view.table.TableView tableView;

    public com.fly.sys.view.table.TableView getTableView() {
        return tableView;
    }

    public void setTableView(com.fly.sys.view.table.TableView tableView) {
        this.tableView = tableView;

        initTableView();
    }

    private void initTableView() {
        if (null != tableView) {
            // 初始化表头
            List<ColAttr> colList = tableView.getColAttr();
            for (final ColAttr colAttr : colList) {
                if ("hyperlink".equals(colAttr.getDisplayStyle())) {
                    TableColumn<Map<String, Object>, Hyperlink> col = new TableColumn<Map<String, Object>, Hyperlink>(colAttr.getDisplayName());
                    col.setPrefWidth(tableView.getColWidth());
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink>, ObservableValue<Hyperlink>>() {
                        @Override
                        public ObservableValue<Hyperlink> call(final TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink> map) {
                            Hyperlink hl = new Hyperlink(map.getValue().get(colAttr.getName()).toString());
                            hl.setStyle("-fx-text-fill: #0000ff;-fx-underline:true;");
                            hl.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    Map<String, String> requestMap = new HashMap<String, String>();
                                    requestMap.put("view", "form");
                                    requestMap.put("classDef", tableView.getClassDef());

                                    /*FormView formView = getFormView();
                                   Form form = new Form();
                                   form.setFormView(formView, map.getValue());*/
                                    //form.setData(map.getValue());
                                }
                            });
                            return new SimpleObjectProperty<Hyperlink>(hl);
                        }
                    });
                    /*col.setCellFactory(new Callback<TableColumn<Map<String, Object>, String>, TableCell<Map<String, Object>, String>>() {
                        @Override
                        public TableCell<Map<String, Object>, String> call(TableColumn<Map<String, Object>, String> mapStringTableColumn) {
                            return new HyperlinkCell();
                        }
                    });*/
                    getColumns().add(col);
                } else {
                    TableColumn<Map<String, Object>, String> col = new TableColumn<Map<String, Object>, String>(colAttr.getDisplayName());
                    col.setPrefWidth(tableView.getColWidth());
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Map<String, Object>, String> map) {
                            return new SimpleStringProperty(map.getValue().get(colAttr.getName()).toString());
                        }
                    });
                    getColumns().add(col);
                }
            }
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println(getSelectionModel().getSelectedIndex());
                    }
                }
            });
            // 初始化表数据
            getItems().addAll(tableView.getColData());
        }
    }
}
