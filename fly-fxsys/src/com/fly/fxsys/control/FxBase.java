package com.fly.fxsys.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fly.fxsys.R;
import com.fly.fxsys.config.SysInfo;
import com.fly.fxsys.util.HttpConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;

import java.io.IOException;

/**
 * @author weijiancai
 */
public class FxBase extends Application {
    protected boolean isApplet;
    protected JSObject browser;

    protected static Stage _stage;
    protected Scene scene;
    protected WindowResizeButton windowResizeButton;
    protected static FxDesktop desktop;

    public static final String URL = "http://localhost:8080";

    public FxBase() {
        try {
            browser = getHostServices().getWebContext();
            isApplet =  browser != null;
        } catch (Exception e) {
            isApplet = false;
        }
        SysInfo.isApplet = isApplet;
    }

    @Override
    public void start(Stage stage) throws Exception {
        _stage = stage;

        desktop = new FxDesktop(stage);

        if (!isApplet) {
            stage.initStyle(StageStyle.UNDECORATED);
            desktop.getStyleClass().add("application");
        } else {
            desktop.getStyleClass().add("applet");
        }

        // 初始化项目
        initProject();

        scene = new Scene(desktop, 1020, 700);
        setSkin(R.skin.DEFAULT);
        // show stage
        stage.setScene(scene);
        stage.show();
    }

    private void initProject() throws IOException {
        HttpConnection conn = new HttpConnection(URL + "/project");
        JSONObject obj = JSON.parseObject(conn.getContentStr());
        desktop.getModuleMenu().initMenu(obj);
    }

    public void setSkin(String skin) {
        scene.getStylesheets().addAll(R.class.getResource("skin/" + skin + "/" + skin + ".css").toExternalForm());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static FxDesktop getDesktop() {
        return desktop;
    }

    public static Stage getStage() {
        return _stage;
    }
}
