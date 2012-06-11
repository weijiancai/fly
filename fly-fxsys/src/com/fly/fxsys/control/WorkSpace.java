package com.fly.fxsys.control;

import com.fly.fxsys.util.HttpConnection;
import com.fly.fxsys.view.FormView;
import com.fly.sys.clazz.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作空间
 *
 * @author weijiancai
 * @version 1.0.0
 */
public class WorkSpace extends StackPane {
    private BorderPane root;
    private VBox top;
    private ToolBar queryBar;
    private Button btn_query;
    private GridPane queryGrid;
    private TableView<Map<String, Object>> tableView;
    private FormView queryForm;

    private ClassDefine clazz;
    private Map<String, TextField> tfMap;

    public WorkSpace() {
        super();
        this.setStyle("-fx-padding:5");
        root = new BorderPane();
        tableView = new TableView<Map<String, Object>>();

        root.setCenter(tableView);

        this.getChildren().add(root);
    }

    public WorkSpace(ClassDefine clazz) {
        this();
        this.clazz = clazz;
        top = new VBox();
        top.setSpacing(10);
        top.setStyle("-fx-padding:0 5 10 5");
        root.setTop(top);
        queryForm = new FormView(clazz.getFormList().get(0));

        initQueryBar();
//        initQueryGrid();
        top.getChildren().add(queryForm);
        initTableView();
    }

    public String getTitle() {
        return clazz.getCname();
    }

    private void initQueryBar() {
        queryBar = new ToolBar();
        btn_query = new Button("查询");
        btn_query.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    List<Map<String, Object>> list = HttpConnection.query (clazz.getName(), null);
                    tableView.setItems(FXCollections.observableList(list));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        queryBar.getItems().addAll(region, btn_query);
        top.getChildren().add(queryBar);
    }

    private void initQueryGrid() {
        queryGrid = new GridPane();
        queryGrid.setHgap(3);
        queryGrid.setVgap(5);

        if (null != clazz && clazz.getClassQueryList() != null && clazz.getClassQueryList().size() > 0) {
            tfMap = new HashMap<String, TextField>();
            ClassQuery classQuery = clazz.getClassQueryList().get(0);

            Label label;
            Region labelGap;
            TextField textField;
            Region fieldGap;
            int idxRow = 0;
            int idxCol = 0;
            for (QueryField field : classQuery.getQueryFieldList()) {
                if (!field.isDisplay()) { // 不显示
                    continue;
                }

                label = new Label(field.getDisplayName());
                queryGrid.add(label, idxCol++, idxRow);

                labelGap = new Region();
                labelGap.setPrefWidth(classQuery.getLabelGap());
                queryGrid.add(labelGap, idxCol++, idxRow);

                textField = new TextField();
                textField.setPrefWidth(field.getWidth());
                /*if (null != data) {
                    textField.setText(data.get(field.getName()) == null ? "" : data.get(field.getName()).toString());
                }*/
                queryGrid.add(textField, idxCol++, idxRow);
                tfMap.put(field.getDisplayName(), textField);

                if (classQuery.getColCount() == 1) {
                    idxCol = 0;
                    idxRow++;
                } else {
                    if (idxCol == classQuery.getColCount() * 4 - 1) {
                        idxCol = 0;
                        idxRow++;
                    } else {
                        fieldGap = new Region();
                        fieldGap.setPrefWidth(classQuery.getFieldGap());
                        queryGrid.add(fieldGap, idxCol++, idxRow);
                    }
                }
            }
        }

        top.getChildren().add(queryGrid);
    }

    @SuppressWarnings("unchecked")
    private void initTableView() {
        if (null != clazz && clazz.getClassTableList() != null && clazz.getClassTableList().size() > 0) {
            // 初始化表头
            ClassTable classTable = clazz.getClassTableList().get(0);
            for (final TableField tableField : classTable.getTableFieldList()) {
                if ("hyperlink".equals(tableField.getDisplayStyle())) {
                    TableColumn<Map<String, Object>, Hyperlink> col = new TableColumn<Map<String, Object>, Hyperlink>(tableField.getDisplayName());
                    col.setPrefWidth(classTable.getColWidth());
                    /*col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink>, ObservableValue<Hyperlink>>() {
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

                                    *//*FormView formView = getFormView();
                                   Form form = new Form();
                                   form.setFormView(formView, map.getValue());*//*
                                    //form.setData(map.getValue());
                                }
                            });
                            return new SimpleObjectProperty<Hyperlink>(hl);
                        }
                    });*/
                    /*col.setCellFactory(new Callback<TableColumn<Map<String, Object>, String>, TableCell<Map<String, Object>, String>>() {
                        @Override
                        public TableCell<Map<String, Object>, String> call(TableColumn<Map<String, Object>, String> mapStringTableColumn) {
                            return new HyperlinkCell();
                        }
                    });*/
                    tableView.getColumns().add(col);
                } else {
                    TableColumn<Map<String, Object>, String> col = new TableColumn<Map<String, Object>, String>(tableField.getDisplayName());
                    col.setPrefWidth(classTable.getColWidth());
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Map<String, Object>, String> map) {
                            ClassField classField = tableField.getClassField();
                            if (null != classField && classField.getName() != null) {
                                Object value = map.getValue().get(classField.getName().toLowerCase());
                                return new SimpleStringProperty(value == null ? "" : value.toString());
                            }
                            return new SimpleStringProperty("");
                        }
                    });
                    col.setPrefWidth(tableField.getColWidth());

                    tableView.getColumns().add(col);
                }
            }
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println(tableView.getSelectionModel().getSelectedIndex());
                    }
                }
            });
            // 初始化表数据
//            getItems().addAll(tableView.getColData());
        }
    }
}
