package com.fly.fxsys.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author weijiancai
 */
public class Workbench extends StackPane {
    public static int count = 0;
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
        final BorderPane bp = BorderPaneBuilder.create().top(HBoxBuilder.create().children(showButton, closeButton).build()).build();
        Button btn = new Button("确定按钮");
        final Label la = new Label("left");
        bp.setLeft(la);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                la.setText("aaaaaaaaaaaaaaaaaaa" + count++);
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new File("C:/tmp.txt"));
                    pw.write("start......\n");
                    pw.flush();
                    System.out.println("-----------------------------------");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("=====================================");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crm?characterEncoding=utf8", "root", "7758521");

                    Label label = new Label(conn.toString());
                    pw.write(conn.toString());
                    la.setText(conn.toString());
                    bp.setCenter(label);
                    conn.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    for(StackTraceElement element : e.getStackTrace()) {
                        pw.write(element.toString() + "\n");
                    }
                    
                }


                la.setText("bbbbbbbbbb" + new File("C:/template.html").getAbsolutePath());

                pw.flush();
                pw.close();
            }
        });
        bp.setBottom(btn);
        try {
            System.out.println("-----------------------------------");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("=====================================");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crm?characterEncoding=utf8", "root", "7758521");
            Label label = new Label(conn.toString());
            bp.setCenter(label);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getChildren().add(bp);
    }
}
