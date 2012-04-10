package com.fly.fxsys.control;

import com.fly.fxsys.R;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;

/**
 * @author weijiancai
 */
public class FxBase extends Application {
    protected boolean isApplet;
    protected JSObject browser;

    protected Stage stage;
    protected Scene scene;
    protected WindowResizeButton windowResizeButton;
    protected BorderPane root;

    public FxBase() {
        try {
            browser = getHostServices().getWebContext();
            isApplet =  browser != null;
        } catch (Exception e) {
            isApplet = false;
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        if (!isApplet) {
            stage.initStyle(StageStyle.UNDECORATED);
            // create window resize button
            windowResizeButton = new WindowResizeButton(stage, 1020,700);
            // create root
            root = new BorderPane() {
                @Override protected void layoutChildren() {
                    super.layoutChildren();
                    windowResizeButton.autosize();
                    windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
                    windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
                }
            };
            root.getStyleClass().add("application");
        } else {
            root = new BorderPane();
            root.getStyleClass().add("applet");
        }

        scene = new Scene(root, 1020, 700);
        setSkin(R.skin.DEFAULT);
        // show stage
        stage.setScene(scene);
        stage.show();
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
}
