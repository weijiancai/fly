package com.fly.fxsys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.fxsys.control.Form;
import com.fly.fxsys.control.Table;
import com.fly.sys.view.form.FormField;
import com.fly.sys.view.form.FormView;
import com.fly.sys.view.table.ColAttr;
import com.fly.sys.view.table.TableView;
import com.fly.sys.view.tree.EasyuiNode;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class FxSys extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        /*URL url = new URL("http://localhost:8080/ProductDefine.class");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        String contentType = conn.getContentType();
        System.out.println(contentType);
        System.out.println(conn.getContent());*/
        HttpConnection conn = new HttpConnection("http://localhost:8080/project");
        /*ProjectDefine projectDefine = JSON.parseObject(conn.getContentStr(), ProjectDefine.class);
        System.out.println(projectDefine);*/
        JSONObject obj = JSON.parseObject(conn.getContentStr());
//        System.out.println(obj);

        // 树形结构
        final TreeItem<String> treeRoot = new TreeItem<String>("Root node");

        JSONArray array = obj.getJSONArray("moduleList");
        TreeItem<String> treeItem;
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            Object superModel = o.get("superModuleId");
            if (superModel == null) {
                treeItem = new TreeItem<String>(o.get("displayName").toString());
                treeRoot.getChildren().add(treeItem);
                iteratorTree(treeItem, o, array);
            }
        }



        /*treeRoot.getChildren().addAll(Arrays.asList(
                new TreeItem<String>("Child Node 1"),
                new TreeItem<String>("Child Node 2"),
                new TreeItem<String>("Child Node 3")));

        treeRoot.getChildren().get(2).getChildren().addAll(Arrays.asList(
                new TreeItem<String>("Child Node 4"),
                new TreeItem<String>("Child Node 5"),
                new TreeItem<String>("Child Node 6"),
                new TreeItem<String>("Child Node 7"),
                new TreeItem<String>("Child Node 8"),
                new TreeItem<String>("Child Node 9"),
                new TreeItem<String>("Child Node 10"),
                new TreeItem<String>("Child Node 11"),
                new TreeItem<String>("Child Node 12")));*/

        final TreeView<String> treeView = new TreeView<String>();
        treeView.setShowRoot(false);
        treeView.setRoot(treeRoot);
        treeRoot.setExpanded(true);

        final BorderPane borderPane = new BorderPane();

        // 请求导航菜单
        // 参数map
        final Map<String, String> params = new HashMap<String, String>();
        params.put("view", "tree");
        params.put("classDef", "Module");
        // 发送请求，接收数据
        //SysAction action = new SysAction();
        //String data = action.send(params);
        String data = "[{  \n" +
                "    \"id\":1,  \n" +
                "    \"text\":\"Folder1\",  \n" +
                "    \"iconCls\":\"icon-save\",  \n" +
                "    \"children\":[{  \n" +
                "        \"text\":\"File1\",  \n" +
                "        \"checked\":true  \n" +
                "    },{  \n" +
                "        \"text\":\"Books\",  \n" +
                "        \"state\":\"open\",  \n" +
                "        \"attributes\":{  \n" +
                "            \"url\":\"/demo/book/abc\",  \n" +
                "            \"price\":100  \n" +
                "        },  \n" +
                "        \"children\":[{  \n" +
                "            \"text\":\"PhotoShop\",  \n" +
                "            \"checked\":true  \n" +
                "        },{  \n" +
                "            \"id\": 8,  \n" +
                "            \"text\":\"Sub Bookds\",  \n" +
                "            \"state\":\"closed\"  \n" +
                "        }]  \n" +
                "    }]  \n" +
                "},{  \n" +
                "    \"text\":\"Languages\",  \n" +
                "    \"state\":\"closed\",  \n" +
                "    \"children\":[{  \n" +
                "        \"text\":\"Java\"  \n" +
                "    },{  \n" +
                "        \"text\":\"C#\"  \n" +
                "    }]  \n" +
                "}]  ";
//        System.out.println(data);
        // 解析数据
        List<EasyuiNode> list = JSON.parseArray(data, EasyuiNode.class);
//        System.out.println(list);

        TreeItem<Object> root = new TreeItem<Object>("Root node");
        //TreeItem<Object> item;
        for (EasyuiNode node : list) {
            iteratorTree(root, node);
        }

        /*final TreeView<Object> treeView = new TreeView<Object>();
        treeView.setShowRoot(true);
        treeView.setRoot(root);
        treeRoot.setExpanded(true);*/

        // 请求Classdef 表格
        Map<String, String> classDefTableMap = new HashMap<String, String>();
        params.put("view", "table");
        params.put("classDef", "ClassDefine");
        final TableView tableView = new TableView();
        List<ColAttr> colAttrList = new ArrayList<ColAttr>();
        colAttrList.add(new ColAttr("className", "类名", "label"));
        colAttrList.add(new ColAttr("cName", "中文名", "label"));
        colAttrList.add(new ColAttr("author", "作者", "label"));
        colAttrList.add(new ColAttr("version", "版本", "label"));
        colAttrList.add(new ColAttr("classDesc", "类描述", "label"));
        colAttrList.add(new ColAttr("look", "查看", "hyperlink"));

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> firstRow = new HashMap<String, Object>();
        firstRow.put("className", "ClassDefine");
        firstRow.put("cName", "类定义");
        firstRow.put("author", "weijiancai");
        firstRow.put("version", "1.0.0");
        firstRow.put("classDesc", "类定义信息");
        firstRow.put("look", "查看明细");
        dataList.add(firstRow);
        Map<String, Object> secondRow = new HashMap<String, Object>();
        secondRow.put("className", "Module");
        secondRow.put("cName", "模块");
        secondRow.put("author", "weijiancai");
        secondRow.put("version", "1.0.0");
        secondRow.put("classDesc", "模块定义信息");
        secondRow.put("look", "查看明细");
        dataList.add(secondRow);
        tableView.setColData(dataList);
        tableView.setColAttr(colAttrList);
        tableView.setColWidth(100);
        tableView.setClassDef("ClassDefine");

        FormView formView = getFormView();
        final Form form = new Form();
        form.setFormView(formView);
        borderPane.setBottom(form);

        String jsonStr = JSON.toJSONString(tableView);
//        System.out.println(jsonStr);
        TableView tv = JSON.parseObject(jsonStr, TableView.class);
        Table table = new Table();
        table.setTableView(tv);
        /*final javafx.scene.control.TableView<Map<String, Object>> fxtv = new javafx.scene.control.TableView<Map<String, Object>>();
        List<ColAttr> colList = tv.getColAttr();
        for (final ColAttr colAttr : colList) {
            if ("hyperlink".equals(colAttr.getDisplayStyle())) {
                TableColumn<Map<String, Object>, Hyperlink> col = new TableColumn<Map<String, Object>, Hyperlink>(colAttr.getDisplayName());
                col.setPrefWidth(tv.getColWidth());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink>, ObservableValue<Hyperlink>>() {
                    @Override
                    public ObservableValue<Hyperlink> call(final TableColumn.CellDataFeatures<Map<String, Object>, Hyperlink> map) {
                        Hyperlink hl = new Hyperlink(map.getValue().get(colAttr.getName()).toString());
                        hl.setStyle("-fx-text-fill: #0000ff;-fx-underline:true;");
                        hl.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                Map<String, String> requestMap = new HashMap<String, String>();
                                requestMap.put("view", "form");
                                requestMap.put("classDef", tableView.getClassDefine());

                                *//*FormView formView = getFormView();
                                Form form = new Form();
                                form.setFormView(formView, map.getValue());*//*
                                form.setData(map.getValue());
                            }
                        });
                        return new SimpleObjectProperty<Hyperlink>(hl);
                    }
                });
                *//*col.setCellFactory(new Callback<TableColumn<Map<String, Object>, String>, TableCell<Map<String, Object>, String>>() {
                    @Override
                    public TableCell<Map<String, Object>, String> call(TableColumn<Map<String, Object>, String> mapStringTableColumn) {
                        return new HyperlinkCell();
                    }
                });*//*
                fxtv.getColumns().add(col);
            } else {
                TableColumn<Map<String, Object>, String> col = new TableColumn<Map<String, Object>, String>(colAttr.getDisplayName());
                col.setPrefWidth(tv.getColWidth());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Map<String, Object>, String> map) {
                        return new SimpleStringProperty(map.getValue().get(colAttr.getName()).toString());
                    }
                });
                fxtv.getColumns().add(col);
            }
        }
        fxtv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println(fxtv.getSelectionModel().getSelectedIndex());
                }
            }
        });
        fxtv.getItems().addAll(tv.getColData());*/


        borderPane.setLeft(treeView);
        borderPane.setCenter(table);
        //borderPane.setBottom(new Hyperlink("超链接"));
        Scene scene = new Scene(borderPane, 1020, 700);
        stage.setScene(scene);
        stage.setTitle("FX SYS");
        stage.show();
    }

    private void iteratorTree(TreeItem<Object> item, EasyuiNode node) {
        TreeItem<Object> local = new TreeItem<Object>(node.getText());
        item.getChildren().add(local);
        if(node.getChildren() != null) {
            for (EasyuiNode n : node.getChildren()) {
                iteratorTree(local, n);
            }
        }
    }

    private void iteratorTree(TreeItem<String> item, JSONObject node, JSONArray array) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (node.get("moduleId").equals(obj.get("superModuleId"))) {
                TreeItem<String> local = new TreeItem<String>(obj.getString("displayName"));
                item.getChildren().add(local);
                iteratorTree(local, obj, array);
            }
        }
    }

    private FormView getFormView() {
        FormView formView = new FormView();
        formView.setClassDef("ClassDefine");
        formView.setColCount(3);
        formView.setColWidth(150);
        formView.setFieldGap(15);
        formView.setLabelGap(5);
        formView.setName("classForm");
        List<FormField> fieldList = new ArrayList<FormField>();
        fieldList.add(new FormField("className", "类名", "textField"));
        fieldList.add(new FormField("cName", "中文名", "textField"));
        fieldList.add(new FormField("author", "作者", "textField"));
        fieldList.add(new FormField("version", "版本", "textField"));
        fieldList.add(new FormField("classDesc", "类描述", "textField"));
        formView.setFieldList(fieldList);

        String jsonStr = JSON.toJSONString(formView);

        FormView fv = JSON.parseObject(jsonStr, FormView.class);

        return fv;
    }

    private GridPane getGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 10));

// DictCategory in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

// Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

// Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);

// House icon in column 1, rows 1-2
        //ImageView imageHouse = new ImageView(new Image(FxSys.class.getResourceAsStream("graphics/house.png")));
        //grid.add(imageHouse, 0, 0, 1, 2);
        grid.add(new TextField(), 0, 0, 1, 2);

// Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);

// Chart in columns 2-3, row 3
        //ImageView imageChart = new ImageView(new Image(FxSys.class.getResourceAsStream("graphics/piechart.png")));
        //grid.add(imageChart, 1, 2, 2, 1);
        grid.add(new Label("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"), 1, 2, 2, 1);

// Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);

        return grid;
    }

    public static void main(String[] args) {
        launch(FxSys.class);
    }
}
