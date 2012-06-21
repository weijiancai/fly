package com.fly.fxsys.control;

import com.fly.fxsys.util.HttpConnection;
import com.fly.fxsys.view.DataGrid;
import com.fly.fxsys.view.FormView;
import com.fly.sys.clazz.ClassDefine;
import com.fly.sys.clazz.ClassField;
import com.fly.sys.clazz.ClassTable;
import com.fly.sys.clazz.TableField;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.meta.DbmsTable;
import com.fly.sys.util.UString;
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
    private DataGrid tableView;
    private FormView editForm;
    private TabPane tabPane;

    private ClassDefine clazz;
    private Map<String, TextField> tfMap;

    public WorkSpace() {
        super();
        this.setStyle("-fx-padding:5");
        root = new BorderPane();

        this.getChildren().add(root);
    }

    public WorkSpace(final ClassDefine clazz) {
        this();
        this.clazz = clazz;
        VBox top = new VBox();
        top.setSpacing(10);
        top.setStyle("-fx-padding:0 5 10 5");
        root.setTop(top);
        FormView queryForm = new FormView(clazz.getFormList().get(0), FormView.QUERY_FORM);
        queryForm.setQueryHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    List<Map<String, Object>> list = HttpConnection.query(clazz.getName(), null);
                    tableView.setItems(FXCollections.observableList(list));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        top.getChildren().add(queryForm);

        tabPane = new TabPane();
        Tab mainTab = new Tab("主表信息");
        tabPane.getTabs().add(mainTab);
        editForm = new FormView(clazz.getFormList().get(0), FormView.EDIT_FORM);
        editForm.setBackHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                editForm.setVisible(false);
                root.setVisible(true);
            }
        });
        tabPane.setVisible(false);
        mainTab.setContent(editForm);
        this.getChildren().add(tabPane);

        tableView = new DataGrid(clazz.getClassTableList().get(0));
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    tabPane.setVisible(true);
                    editForm.initUIData(tableView.getSelectionModel().getSelectedItem());
                    root.setVisible(false);
                }
            }
        });

        root.setCenter(tableView);

        try {
            initItemTab();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initItemTab() throws ClassNotFoundException, IOException {
        ClassDefine classDefine;
        Tab tab;
        for (String className : clazz.getItemClassNameList()) {
            System.out.println(className);
            classDefine = HttpConnection.getClassDefine(className);
            tab = new Tab(classDefine.getCname());
            tabPane.getTabs().add(tab);
        }
    }

    public String getTitle() {
        return clazz.getCname();
    }
}
