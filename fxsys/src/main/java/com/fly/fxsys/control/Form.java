package com.fly.fxsys.control;

import com.fly.sys.view.form.FormField;
import com.fly.sys.view.form.FormView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 */
public class Form extends GridPane {
    private FormView formView;
    private Map<String, Object> data;
    private Map<String, TextField> tfMap;
    
    private HBox buttonBox;
    public Button addButton = new Button("增加");
    public Button modifyButton = new Button("修改");

    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
        initGrid();
    }

    public void setFormView(FormView formView, Map<String, Object> data) {
        this.formView = formView;
        this.data = data;
        initGrid();
    }

    private void initGrid() {
        if (formView != null) {
            tfMap = new HashMap<String, TextField>();
            
            Label label;
            Region labelGap;
            TextField textField;
            Region fieldGap;
            int idxRow = 0;
            int idxCol = 0;
            for (FormField field : formView.getFieldList()) {
                label = new Label(field.getDisplayName());
                add(label, idxCol++, idxRow);

                labelGap = new Region();
                labelGap.setPrefWidth(formView.getLabelGap());
                add(labelGap, idxCol++, idxRow);

                textField = new TextField();
                if (null != data) {
                    textField.setText(data.get(field.getName()) == null ? "" : data.get(field.getName()).toString());
                }
                add(textField, idxCol++, idxRow);
                tfMap.put(field.getName(), textField);

                if (formView.getColCount() == 1) {
                    idxCol = 0;
                    idxRow++;
                } else {
                    if (idxCol == formView.getColCount() * 4 - 1) {
                        idxCol = 0;
                        idxRow++;
                    } else {
                        fieldGap = new Region();
                        fieldGap.setPrefWidth(formView.getFieldGap());
                        add(fieldGap, idxCol++, idxRow);
                    }
                }
            }

            buttonBox = new HBox();
            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            buttonBox.getChildren().addAll(region, new Button("确定"), addButton, modifyButton);
            add(buttonBox, 0, idxRow + 1, formView.getColCount() * 4 - 1, 1);
        }
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
        for (String name : tfMap.keySet()) {
            tfMap.get(name).setText(data.get(name) == null ? "" : data.get(name).toString());
        }
    }
}
