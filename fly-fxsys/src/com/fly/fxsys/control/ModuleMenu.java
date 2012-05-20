package com.fly.fxsys.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.fxsys.control.model.ModuleMenuItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ModuleMenu extends BorderPane {
    private FxDesktop desktop;
    private TreeView<ModuleMenuItem> treeView;

    public ModuleMenu(FxDesktop fxDesktop) {
        this.desktop = fxDesktop;
        treeView = new TreeView<ModuleMenuItem>();
        treeView.setShowRoot(false);

        this.setCenter(treeView);

        treeView.getSelectionModel().selectionModeProperty().addListener(new ChangeListener<SelectionMode>() {
            @Override
            public void changed(ObservableValue<? extends SelectionMode> observableValue, SelectionMode selectionMode, SelectionMode selectionMode1) {
                desktop.getWorkbench().getChildren().add(new Label(treeView.getSelectionModel().getSelectedItem().getValue().getDisplayName()));
            }
        });
        treeView.selectionModelProperty().addListener(new ChangeListener<MultipleSelectionModel<TreeItem<ModuleMenuItem>>>() {
            @Override
            public void changed(ObservableValue<? extends MultipleSelectionModel<TreeItem<ModuleMenuItem>>> observableValue, MultipleSelectionModel<TreeItem<ModuleMenuItem>> treeItemMultipleSelectionModel, MultipleSelectionModel<TreeItem<ModuleMenuItem>> treeItemMultipleSelectionModel1) {
                desktop.getWorkbench().getChildren().add(new Label(treeView.getSelectionModel().getSelectedItem().getValue().getDisplayName()));
            }
        });
    }

    public void initMenu(JSONObject obj) {
        final TreeItem<ModuleMenuItem> treeRoot = new TreeItem<ModuleMenuItem>();

        JSONArray array = obj.getJSONArray("moduleList");
        TreeItem<ModuleMenuItem> treeItem;
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            Object superModel = o.get("superModuleId");
            if (superModel == null) {
                treeItem = new TreeItem<ModuleMenuItem>(new ModuleMenuItem(o));
                treeRoot.getChildren().add(treeItem);
                iteratorTree(treeItem, o, array);
            }
        }

        treeView.setRoot(treeRoot);
    }

    private void iteratorTree(TreeItem<ModuleMenuItem> item, JSONObject node, JSONArray array) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (node.get("moduleId").equals(obj.get("superModuleId"))) {
                TreeItem<ModuleMenuItem> local = new TreeItem<ModuleMenuItem>(new ModuleMenuItem(obj));
                item.getChildren().add(local);
                iteratorTree(local, obj, array);
            }
        }
    }
}
