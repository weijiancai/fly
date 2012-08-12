package com.fly.fxsys.control;

import com.fly.fxsys.util.HttpConnection;
import com.fly.fxsys.view.DataGrid;
import com.fly.fxsys.view.FormView;
import com.fly.sys.clazz.ClassDefine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

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
public class WorkSpace extends BorderPane {
    private StackPane stackPane;
    private DataGrid tableView;
    private ControlBar controlBar;
    private FormView queryForm;

    private ClassDefine clazz;
    private Map<TabPane, FormView> editFormMap = new HashMap<TabPane, FormView>();

    public WorkSpace() {
        super();
        this.setStyle("-fx-padding:5");

        controlBar = new ControlBar();
        this.setTop(controlBar);

        stackPane = new StackPane();
        this.setCenter(stackPane);
    }

    public WorkSpace(final ClassDefine clazz) {
        this();
        this.clazz = clazz;
        VBox center = new VBox();
        center.setSpacing(10);
        center.setStyle("-fx-padding:0 5 10 5");
        center.setPrefHeight(800);

        queryForm = new FormView(clazz.getFormList().get(0));
        center.getChildren().add(queryForm);

        tableView = new DataGrid(clazz.getClassTableList().get(0));
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    initMainTab(tableView, clazz);
                }
            }
        });

        center.getChildren().add(tableView);
        stackPane.getChildren().add(center);

        registerEvent();
    }

    private void initMainTab(DataGrid superDataGrid, ClassDefine clazz) {
        TabPane tabPane = new TabPane();
        Tab mainTab = new Tab("主表信息");
        tabPane.getTabs().add(mainTab);
        FormView editForm = new FormView(clazz.getFormList().get(0));
        editForm.initUIData(superDataGrid.getSelectionModel().getSelectedItem());
        editFormMap.put(tabPane, editForm);
        mainTab.setContent(editForm);

        try {
            initItemTab(tabPane, superDataGrid, clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stackPane.getChildren().add(tabPane);
    }

    private void registerEvent() {
        // 查询事件处理
        controlBar.setQueryHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    List<Map<String, Object>> list = HttpConnection.query(clazz.getName(), queryForm.getQueryConditionMap());
                    tableView.setItems(FXCollections.observableList(list));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        // 关闭事件处理
        controlBar.setCloseHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (stackPane.getChildren().size() > 1) {
                    stackPane.getChildren().remove(stackPane.getChildren().size() - 1);
                }
            }
        });
        // 保存事件处理
        controlBar.setSaveHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (stackPane.getChildren().size() > 1) {
                    TabPane curTabPane = (TabPane) stackPane.getChildren().get(stackPane.getChildren().size() - 1);
                    FormView editForm = editFormMap.get(curTabPane);
                    try {
                        editForm.update();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initItemTab(TabPane tabPane, final DataGrid superDataGrid, ClassDefine clazz) throws ClassNotFoundException, IOException {
        ClassDefine classDefine;
        Tab tab;
        DataGrid dataGrid;
        for (String className : clazz.getItemClassNameList()) {
            classDefine = HttpConnection.getClassDefine(className);
            tab = new Tab(classDefine.getCname());
            tabPane.getTabs().add(tab);

            dataGrid = new DataGrid(classDefine.getClassTableList().get(0));
            tab.setContent(dataGrid);

            final DataGrid finalDataGrid = dataGrid;
            final ClassDefine finalClassDefine = classDefine;
            tab.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                    if (newVal) {
                        try {
                            Map<String, String> conditionMap = new HashMap<String, String>();
                            addConditionMap(conditionMap, superDataGrid, finalDataGrid);
                            List<Map<String, Object>> list = HttpConnection.query(finalClassDefine.getName(), conditionMap);
                            finalDataGrid.setItems(FXCollections.observableList(list));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            dataGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        initMainTab(finalDataGrid, finalClassDefine);
                    }
                }
            });
        }
    }

    private void addConditionMap(Map<String, String> conditionMap, DataGrid superDataGrid, DataGrid finalDataGrid) {
        List<String> pkColumnIds = superDataGrid.getPkColumnIds();
        for (String fkKey : finalDataGrid.getFkColumnMap().keySet()) {
            String pkId = finalDataGrid.getFkColumnMap().get(fkKey);
            if (pkColumnIds.contains(pkId)) {
                Object value = superDataGrid.getSelectedItem().get(superDataGrid.getClassFieldByColId(pkId).getName().toLowerCase());
                if (value != null) {
                    conditionMap.put(fkKey, value.toString());
                }
            }
        }
    }

    public String getTitle() {
        return clazz.getCname();
    }
}
