package com.fly.fxsys.control;

import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

/**
 * @author weijiancai
 */
public class FxDesktop extends StackPane {
    public FxDesktop() {
        //this.setStyle("-fx-background-color: #ff0000");
        TextArea tx = new TextArea();
        tx.setText("fjwiefjwiefji");
        //tx.setManaged(false);
        //tx.setLayoutX(50);
        //tx.setLayoutY(70);
        tx.setMinSize(500,400);
        //tx.autosize();

        StackPane sp = StackPaneBuilder.create().layoutX(50).layoutY(70).minWidth(300).minHeight(200)
                .style("-fx-background-color:#110000").managed(true).children(new Label("fjwiefwjfiwjefiwjfiewe")).build();
        //sp.requestLayout();
//        sp.autosize();
        Text addedTextRef = TextBuilder.create()
                .layoutX(50)
                .layoutY(80)
                .textOrigin(VPos.TOP)
                .fill(Color.BLUE)
                .font(Font.font("Sans Serif", FontWeight.BOLD, 16))
                .text("fjwiefjwiefjwiefjwiefjwiefjwiefeewfwe")
                .managed(true)
                .build();

        this.getChildren().addAll(new Button("Test"));
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
