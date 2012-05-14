package com.fly.fxsys.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author weijiancai
 */
public class FxDesktop extends BorderPane {
    private Banner banner;
    private Workbench workbench;

    public FxDesktop(final Stage stage) {
        banner = new Banner(stage);
        this.setTop(banner);
        workbench = new Workbench();
        this.setCenter(workbench);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("ScreenX = " + e.getScreenX());
                System.out.println("ScreenY = " + e.getScreenY());
                System.out.println("SceneX = " + e.getSceneX());
                System.out.println("SceneY = " + e.getSceneY());
            }
        });
    }

    public void showDialog(Dialog dialog) {
        if (workbench.getChildren().contains(dialog)) {
            return;
        }
        dialog.setDesktop(this);
        if (dialog.isModal()) {
            workbench.getChildren().add(dialog.getModalPane());
        }
        workbench.getChildren().add(dialog);
        dialog.autosize();

        dialog.layoutXProperty().bind(this.widthProperty().add(-dialog.getWidth()).divide(2));
        dialog.layoutYProperty().bind(this.heightProperty().add(-dialog.getHeight()).divide(2));
    }

    public void closeDialog(Dialog dialog) {
        workbench.getChildren().removeAll(dialog.getModalPane(), dialog);
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
