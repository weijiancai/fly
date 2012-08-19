package com.fly.fxsys.view;

import com.fly.sys.clazz.ClassField;
import com.fly.sys.clazz.ClassTable;
import com.fly.sys.clazz.TableField;
import com.fly.sys.db.meta.DbmsColumn;
import com.fly.sys.util.UString;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DataGrid extends TableView<Map<String, Object>> implements View {
    private ClassTable classTable;
    private Map<String, ClassField> columnClassFieldMap = new HashMap<String, ClassField>();

    public DataGrid(ClassTable classTable) {
        this.classTable = classTable;
        for (TableField field : classTable.getTableFieldList()) {
            DbmsColumn column = field.getClassField().getColumn();
            columnClassFieldMap.put(column.getId(), field.getClassField());
        }

        initUI();
    }

    @Override
    public void initUI() {
        initTableColumn();
    }

    @Override
    public void initUIData(Map<String, Object> data) {
    }

    private void initTableColumn() {
        for (final TableField tableField : classTable.getTableFieldList()) {
            if ("hyperlink".equals(tableField.getDisplayStyle())) {
                TableColumn<Map<String, Object>, Hyperlink> col = new TableColumn<Map<String, Object>, Hyperlink>(tableField.getDisplayName());
                col.setPrefWidth(classTable.getColWidth());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink>, ObservableValue<Hyperlink>>() {

                    @Override
                    public ObservableValue<Hyperlink> call(TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink> map) {
                        Hyperlink hl = new Hyperlink(map.getValue().get(tableField.getId()).toString());
                        hl.setStyle("-fx-text-fill: #0000ff;-fx-underline:true;");

                        return new SimpleObjectProperty<Hyperlink>(hl);
                    }
                });

                getColumns().add(col);
            } else {
                TableColumn<Map<String, Object>, String> col = new TableColumn<Map<String, Object>, String>(tableField.getDisplayName());
                col.setPrefWidth(classTable.getColWidth());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Map<String, Object>, String> map) {
                        ClassField classField = tableField.getClassField();
                        if (null != classField && classField.getName() != null) {
                            Object value = map.getValue().get(classField.getName().toLowerCase());
                            if (classField.getDictCategory() != null && value != null && value instanceof String) {
                                value = classField.getDictCategory().getCodeName(value.toString());
                            }
                            return new SimpleStringProperty(value == null ? "" : value.toString());
                        }
                        return new SimpleStringProperty("");
                    }
                });
                col.setPrefWidth(tableField.getColWidth());

                getColumns().add(col);
            }
        }
    }

    public Map<String, Object> getSelectedItem() {
        return this.getSelectionModel().getSelectedItem();
    }

    public Map<String, String> getFkColumnMap() {
        Map<String, String> result = new HashMap<String, String>();
        for (TableField field : classTable.getTableFieldList()) {
            DbmsColumn column = field.getClassField().getColumn();
            if (column.isFk() && column.getFkColumn() != null) {
                result.put(column.getName(), column.getFkColumn().getId());
            }
        }

        return result;
    }

    public List<String> getPkColumnIds() {
        List<String> result = new ArrayList<String>();
        for (TableField field : classTable.getTableFieldList()) {
            DbmsColumn column = field.getClassField().getColumn();
            if (column.isPk()) {
                result.add(column.getId());
            }
        }
        return result;
    }

    public ClassField getClassFieldByColId(String columnId) {
        return columnClassFieldMap.get(columnId);
    }
}
