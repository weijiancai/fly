package com.fly.fxsys.view;

import com.fly.sys.clazz.ClassForm;
import com.fly.sys.clazz.FormField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class FormView extends BorderPane implements View {
    public static final int QUERY_FORM = 0;
    public static final int EDIT_FORM = 1;

    private Button btn_back;
    private Button btn_query;

    private EventHandler<ActionEvent> backHandler;
    private EventHandler<ActionEvent> queryHandler;

    private ClassForm form;
    private int type;

    public FormView(ClassForm form, int type) {
        this.form = form;
        this.type = type;
        
        initUI();
    }

    @Override
    public void initUI() {
        initToolBar();
        initFormGridPane();
    }

    private void initToolBar() {
        ToolBar toolBar = new ToolBar();
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        toolBar.getItems().add(region);

        btn_back = new Button("后退");

        btn_query = new Button("查询");

        if (type == QUERY_FORM) {
            toolBar.getItems().add(btn_query);
        } else if (type == EDIT_FORM) {
            toolBar.getItems().add(btn_back);
        }

        this.setTop(toolBar);
    }

    private void initFormGridPane() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(form.getHgap());
        formGrid.setVgap(form.getVgap());

        Label label;
        Region labelGap;
        TextField textField;
        Region fieldGap;
        int idxRow = 0;
        int idxCol = 0;
        for (FormField field : form.getFieldList()) {
            if (!field.isDisplay()) { // 不显示
                continue;
            }

            // 单行
            if (field.isSingleLine()) {
                idxRow++;
                formGrid.add(new Label(field.getDisplayName()), 0, idxRow);
                labelGap = new Region();
                labelGap.setPrefWidth(form.getLabelGap());
                formGrid.add(labelGap, 1, idxRow);
                textField = new TextField();
                textField.setPrefHeight(field.getHeight());
//                textField.setText(field);
                //tf.setAlignment(Pos.TOP_LEFT);
                formGrid.add(textField, 2, idxRow, form.getColCount() * 4 - 5, 1);
                idxCol = 0;
                idxRow++;

                continue;
            }

            label = new Label(field.getDisplayName());
            formGrid.add(label, idxCol++, idxRow);

            labelGap = new Region();
            labelGap.setPrefWidth(form.getLabelGap());
            formGrid.add(labelGap, idxCol++, idxRow);

            textField = new TextField();
            textField.setPrefWidth(field.getWidth());
            /*if (null != data) {
                textField.setText(data.get(field.getName()) == null ? "" : data.get(field.getName()).toString());
            }*/
            formGrid.add(textField, idxCol++, idxRow);
//            tfMap.put(field.getDisplayName(), textField);

            if (form.getColCount() == 1) {
                idxCol = 0;
                idxRow++;
            } else {
                if (idxCol == form.getColCount() * 4 - 1) {
                    idxCol = 0;
                    idxRow++;
                } else {
                    fieldGap = new Region();
                    fieldGap.setPrefWidth(form.getFieldGap());
                    formGrid.add(fieldGap, idxCol++, idxRow);
                }
            }
        }

        this.setCenter(formGrid);
    }

    @Override
    public void initUIData() {
    }

    public EventHandler<ActionEvent> getBackHandler() {
        return backHandler;
    }

    public void setBackHandler(EventHandler<ActionEvent> backHandler) {
        this.backHandler = backHandler;
        btn_back.setOnAction(backHandler);
    }

    public EventHandler<ActionEvent> getQueryHandler() {
        return queryHandler;
    }

    public void setQueryHandler(EventHandler<ActionEvent> queryHandler) {
        this.queryHandler = queryHandler;
        btn_query.setOnAction(queryHandler);
    }
}
