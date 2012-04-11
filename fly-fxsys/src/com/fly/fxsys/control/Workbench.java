package com.fly.fxsys.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;

/**
 * @author weijiancai
 */
public class Workbench extends StackPane {
    public Workbench() {
        final Dialog dialog = new Dialog(false);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FxBase.getDesktop().closeDialog(dialog);
            }
        });
        Button showButton = new Button("Show");
        showButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FxBase.getDesktop().showDialog(dialog);
            }
        });
        BorderPane bp = BorderPaneBuilder.create().top(HBoxBuilder.create().children(showButton, closeButton).build()).build();
        this.getChildren().add(bp);
    }
}
