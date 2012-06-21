/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fly.fxsys;

import com.fly.fxsys.control.Dialog;
import com.fly.fxsys.control.FxDesktop;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class FxSys extends Application {
    Label htmlLabel;
    Popup alertPopup;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        stage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                Popup alertPopup = createAlertPopup("Hello World!");
                alertPopup.show(stage,
                        (stage.getWidth() - alertPopup.getWidth()) / 2 + stage.getX(),
                        (stage.getHeight() - alertPopup.getHeight()) / 2 + stage.getY());
            }
        });

//        StackPane desktop = new StackPane();
//        desktop.getChildren().add(btn);
        final FxDesktop root = new FxDesktop(stage);
        final Dialog dialog = new Dialog(true);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.closeDialog(dialog);
            }
        });
        Button showButton = new Button("Show");
        showButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.showDialog(dialog);
            }
        });
        BorderPane bp = BorderPaneBuilder.create().top(HBoxBuilder.create().children(showButton, closeButton).build()).build();


        root.getChildren().add(bp);

        //dialog.show();
        root.showDialog(dialog);
        stage.setScene(new Scene(root, 900, 550));
        stage.initStyle(StageStyle.DECORATED);
        //stage.setOpacity(0);
        stage.show();
    }

    public Popup createAlertPopup(String text) {
        Button okButton;
        alertPopup = PopupBuilder.create()
                .content(
                        StackPaneBuilder.create()
                                .children(
                                        RectangleBuilder.create()
                                                .width(300)
                                                .height(200)
                                                .arcWidth(20)
                                                .arcHeight(20)
                                                .fill(Color.LIGHTBLUE)
                                                .stroke(Color.GRAY)
                                                .strokeWidth(2)
                                                .build(),
                                        BorderPaneBuilder.create()
                                                .center(
                                                        htmlLabel = LabelBuilder.create()
                                                                .text(text)
                                                                .wrapText(true)
                                                                .maxWidth(280)
                                                                .maxHeight(140)
                                                                .build()
                                                )
                                                .bottom(
                                                        okButton = ButtonBuilder.create()
                                                                .text("OK")
                                                                .onAction(new EventHandler<ActionEvent>() {
                                                                    @Override public void handle(ActionEvent e) {
                                                                        alertPopup.hide();
                                                                    }
                                                                })
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        BorderPane.setAlignment(okButton, Pos.CENTER);
        BorderPane.setMargin(okButton, new Insets(10, 0, 10, 0));
        return alertPopup;
    }
}
