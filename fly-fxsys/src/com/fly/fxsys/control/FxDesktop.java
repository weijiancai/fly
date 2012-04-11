package com.fly.fxsys.control;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author weijiancai
 */
public class FxDesktop extends BorderPane {
    private Stage stage;

    private Banner banner;
    private Workbench workbench;

    public FxDesktop(final Stage stage) {
        this.stage = stage;

        banner = new Banner(stage);
        this.setTop(banner);
        workbench = new Workbench();
        this.setCenter(workbench);
    }

    public void showDialog(Dialog dialog) {
        if (workbench.getChildren().contains(dialog)) {
            return;
        }
        if (dialog.isModal()) {
            workbench.getChildren().add(dialog.getModalPane());
        }
        workbench.getChildren().add(dialog);
        dialog.autosize();
        System.out.println(dialog.getPrefWidth());
        System.out.println(dialog.getPrefHeight());

        dialog.layoutXProperty().bind(this.widthProperty().add(-dialog.getWidth()).divide(2));
        dialog.layoutYProperty().bind(this.heightProperty().add(-dialog.getHeight()).divide(2));
    }

    public void closeDialog(Dialog dialog) {
        workbench.getChildren().removeAll(dialog.getModalPane(), dialog);
    }
}
