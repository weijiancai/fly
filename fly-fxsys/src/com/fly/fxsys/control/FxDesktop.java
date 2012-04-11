package com.fly.fxsys.control;

import com.fly.fxsys.config.SysInfo;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author weijiancai
 */
public class FxDesktop extends StackPane {
    private Stage stage;

    public FxDesktop(Stage stage) {
        this.stage = stage;

        if (!SysInfo.isApplet) {
            // 设置ResizeButton
            this.getChildren().add(new WindowResizeButton(this, stage.getWidth(), stage.getHeight()));
        }
    }

    public void showDialog(Dialog dialog) {
        if (dialog.isModal()) {
            this.getChildren().add(dialog.getModalPane());
        }
        this.getChildren().add(dialog);
        dialog.autosize();
        System.out.println(dialog.getPrefWidth());
        System.out.println(dialog.getPrefHeight());

        dialog.layoutXProperty().bind(this.widthProperty().add(-dialog.getWidth()).divide(2));
        dialog.layoutYProperty().bind(this.heightProperty().add(-dialog.getHeight()).divide(2));
    }

    public void closeDialog(Dialog dialog) {
        this.getChildren().removeAll(dialog.getModalPane(), dialog);
    }
}
