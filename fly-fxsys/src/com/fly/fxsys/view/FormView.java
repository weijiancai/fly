package com.fly.fxsys.view;

import com.fly.fxsys.control.IValue;
import com.fly.fxsys.control.valuectl.*;
import com.fly.fxsys.util.HttpConnection;
import com.fly.sys.clazz.ClassForm;
import com.fly.sys.clazz.FormField;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.db.query.QueryCondition;
import com.fly.sys.dict.DataType;
import com.fly.sys.dict.DisplayStyle;
import com.fly.sys.util.UString;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class FormView extends BorderPane implements View {
    private Map<String, IValue> fieldNodeMap = new HashMap<String, IValue>();
    private Map<String, FormField> fieldMap = new HashMap<String, FormField>();
    private Map<String, FormField> columnNameMap = new HashMap<String, FormField>();
    private Map<String, Object> oldDataMap;

    private ClassForm form;

    public FormView(ClassForm form) {
        this.form = form;

        initUI();
    }

    @Override
    public void initUI() {
        initFormGridPane();
    }

    private void initFormGridPane() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(form.getHgap());
        formGrid.setVgap(form.getVgap());

        Label label;
        Region labelGap;
        IValue node;
        Region fieldGap;
        int idxRow = 0;
        int idxCol = 0;
        for (FormField field : form.getFieldList()) {
            if (!field.isDisplay()) { // 不显示
                continue;
            }
            columnNameMap.put(field.getClassField().getName().toLowerCase(), field);
            fieldMap.put(field.getId(), field);

            node = getValueNode(field);
            // 单行
            if (field.isSingleLine()) {
                idxRow++;
                formGrid.add(new Label(field.getDisplayName()), 0, idxRow);
                labelGap = new Region();
                labelGap.setPrefWidth(form.getLabelGap());
                formGrid.add(labelGap, 1, idxRow);
                fieldNodeMap.put(field.getId(), node);
                formGrid.add((Node)node, 2, idxRow, form.getColCount() * 4 - 3, 1);
                idxCol = 0;
                idxRow++;

                continue;
            }

            label = new Label(field.getDisplayName());
            formGrid.add(label, idxCol++, idxRow);

            labelGap = new Region();
            labelGap.setPrefWidth(form.getLabelGap());
            formGrid.add(labelGap, idxCol++, idxRow);

            fieldNodeMap.put(field.getId(), node);
            formGrid.add((Node)node, idxCol++, idxRow);

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

    private IValue getValueNode(FormField field) {
        IValue node;
        if (DisplayStyle.TEXT_AREA == field.getDisplayStyle()) {
            VTextArea textArea = new VTextArea();
            textArea.setPrefHeight(field.getHeight());
            node = textArea;
        } else if (DisplayStyle.PASSWORD == field.getDisplayStyle()) {
            VPasswordField passwordField = new VPasswordField();
            passwordField.setPrefWidth(field.getWidth());
            node = passwordField;
        } else if (DisplayStyle.COMBO_BOX == field.getDisplayStyle()) {
            VComboBox comboBox = new VComboBox();
            comboBox.setPrefWidth(field.getWidth());
            node = comboBox;
        } else {
            if (DataType.DATE == field.getClassField().getColumn().getDataTypeEnum()) {
                if ("0".equals(field.getClassForm().getFormType())) {
                    VDateRangeField dateField = new VDateRangeField();
                    dateField.setPrefWidth(field.getWidth() + 0.0);
                    node = dateField;
                } else {
                    VDateField dateField = new VDateField();
                    dateField.setDateTextWidth(field.getWidth() + 0.0);
                    node = dateField;
                }
            } else {
                VTextField textField = new VTextField();
                textField.setPrefWidth(field.getWidth());
                node = textField;
            }
        }
        return node;
    }

    @Override
    public void initUIData(Map<String, Object> data) {
        oldDataMap = data;
        FormField field;
        IValue node;
        Object value;
        for (String key : data.keySet()) {
            field = columnNameMap.get(key);
            if (null != field) {
                node = fieldNodeMap.get(field.getId());
                if (null != node) {
                    value = data.get(key);
                    node.setValue(value == null ? "" : value.toString());
                }
            }
        }
    }

    public void update() throws IOException {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Map<String, Object> newValueMap = new HashMap<String, Object>();
        String newVal;
        for (String key : fieldNodeMap.keySet()) {
            IValue node = fieldNodeMap.get(key);
            newVal = node.value();
            FormField formField = fieldMap.get(key);
            Object oldValue = oldDataMap.get(formField.getClassField().getName().toLowerCase());
            if (UString.isNotEmpty(newVal) && !newVal.equals(oldValue == null ? "" : oldValue.toString())) {
                String columnName = formField.getClassField().getColumn().getName();
                valueMap.put(columnName, newVal);
                newValueMap.put(formField.getClassField().getName().toLowerCase(), newVal);
            }
        }

        String tableName = null;
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        for (String key : fieldMap.keySet()) {
            FormField field = fieldMap.get(key);
            DbmsColumn column = field.getClassField().getColumn();
            if (column.isPk()) {
                conditionMap.put(column.getName(), oldDataMap.get(field.getClassField().getName().toLowerCase()));
                tableName = column.getTable().getName();
            }
        }

        // 发送请求，保存数据
        HttpConnection.update(form.getClassDefine().getName(), valueMap, conditionMap, tableName);

        // 替换掉旧值
        oldDataMap.putAll(newValueMap);
    }

    public QueryCondition getQueryCondition() {
        QueryCondition result = new QueryCondition();

        DbmsColumn column;
        for (String key : fieldNodeMap.keySet()) {
            IValue node = fieldNodeMap.get(key);
            FormField formField = fieldMap.get(key);
            column = formField.getClassField().getColumn();
            if (DataType.DATE == column.getDataTypeEnum()) {
                String[] values = node.values();
                if (values != null && values.length == 2) {
                    if (UString.isNotEmpty(values[0])) {
                        result.addCondition(column.getName(), ">=", values[0]);
                    }
                    if (UString.isNotEmpty(values[1])) {
                        result.addCondition(column.getName(), "<", values[1]);
                    }
                }
            } else {
                result.addCondition(column.getName(), "=", node.value());
            }
        }

        return result;
    }
}
