package com.fly.fxsys.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.fxsys.control.model.ModuleMenuItem;
import com.fly.sys.project.ProjectDefine;
import com.fly.sys.project.ProjectModule;
import com.fly.sys.util.UString;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ModuleMenu extends BorderPane {
    private FxDesktop desktop;
    private TreeView<ProjectModule> treeView;

    public ModuleMenu(FxDesktop fxDesktop) {
        this.desktop = fxDesktop;
        treeView = new TreeView<ProjectModule>();
        treeView.setShowRoot(false);

        this.setCenter(treeView);

        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<ProjectModule>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<ProjectModule>> observableValue, TreeItem<ProjectModule> projectModuleTreeItem, TreeItem<ProjectModule> projectModuleTreeItem1) {
                ProjectModule module = treeView.getSelectionModel().getSelectedItem().getValue();
                if (module.getModule().getClassDefine() != null) {
                    Workspace workspace = new Workspace(module.getModule().getClassDefine());
                    desktop.getWorkbench().addWorkspace(workspace);
                }
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

//        treeView.setRoot(treeRoot);
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

    private void iteratorTree(TreeItem<ProjectModule> item, ProjectModule node, List<ProjectModule> array) {
        for (int i = 0; i < array.size(); i++) {
            ProjectModule obj = array.get(i);
            if (node.getModuleId().equals(obj.getSuperModuleId())) {
                TreeItem<ProjectModule> local = new TreeItem<ProjectModule>(obj);
                item.getChildren().add(local);
                iteratorTree(local, obj, array);
            }
        }
    }

    public void initMenu(ProjectDefine project) {
        final TreeItem<ProjectModule> treeRoot = new TreeItem<ProjectModule>();

        List<ProjectModule> array = project.getModuleList();
        TreeItem<ProjectModule> treeItem;
        for (int i = 0; i < array.size(); i++) {
            ProjectModule o = array.get(i);
            String superModelId = o.getSuperModuleId();
            if (UString.isEmpty(superModelId)) {
                treeItem = new TreeItem<ProjectModule>(o);
                treeRoot.getChildren().add(treeItem);
                iteratorTree(treeItem, o, array);
            }
        }

        treeView.setRoot(treeRoot);
    }
}
