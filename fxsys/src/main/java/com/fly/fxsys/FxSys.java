package com.fly.fxsys;

import com.alibaba.fastjson.JSON;
import com.fly.sys.view.tree.EasyuiNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijiancai
 */
public class FxSys extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // 树形结构
        final TreeItem<String> treeRoot = new TreeItem<String>("Root node");
        treeRoot.getChildren().addAll(Arrays.asList(
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
                new TreeItem<String>("Child Node 12")));

        /*final TreeView<String> treeView = new TreeView<String>();
        treeView.setShowRoot(true);
        treeView.setRoot(treeRoot);
        treeRoot.setExpanded(true);*/
        
        // 请求导航菜单
        // 参数map
        Map<String, String> params = new HashMap<String, String>();
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
        System.out.println(data);
        // 解析数据
        List<EasyuiNode> list = JSON.parseArray(data, EasyuiNode.class);
        System.out.println(list);

        TreeItem<Object> root = new TreeItem<Object>("Root node");
        //TreeItem<Object> item;
        for(EasyuiNode node : list) {
            iteratorTree(root, node);
        }

        final TreeView<Object> treeView = new TreeView<Object>();
        treeView.setShowRoot(true);
        treeView.setRoot(root);
        treeRoot.setExpanded(true);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(treeView);
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

    public static void main(String[] args) {
        launch(FxSys.class);
    }
}
