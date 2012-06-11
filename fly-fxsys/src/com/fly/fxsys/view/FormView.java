package com.fly.fxsys.view;

import com.fly.sys.clazz.ClassForm;
import com.fly.sys.clazz.FormField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class FormView extends BorderPane implements View {
    private GridPane formGrid;
    
    private ClassForm form;

    public FormView(ClassForm form) {
        this.form = form;
        
        initUI();
    }

    @Override
    public void initUI() {
        formGrid = new GridPane();
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
}
