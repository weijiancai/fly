package com.fly.fxsys.view;

import com.fly.sys.clazz.ClassField;
import com.fly.sys.clazz.ClassTable;
import com.fly.sys.clazz.TableField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.Map;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DataGrid extends TableView<Map<String, Object>> implements View {
    private ClassTable classTable;

    public DataGrid(ClassTable classTable) {
        this.classTable = classTable;

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
}
