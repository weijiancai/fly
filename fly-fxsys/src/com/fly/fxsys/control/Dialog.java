package com.fly.fxsys.control;

import com.fly.fxsys.config.SysInfo;
import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * @author weijiancai
 */
public class Dialog extends BorderPane {
    private FxDesktop desktop;
    private boolean isModal;
    private Pane modalPane;

    private TopBar topBar;



    public Dialog(FxDesktop desktop, String color, boolean modal) {
        this.desktop = desktop;
        this.isModal = modal;

        this.setStyle("-fx-background-color:" + color);
        this.setManaged(false);
        this.setPrefSize(300, 200);

        if (isModal) {
            modalPane = new Pane();
            modalPane.setStyle("-fx-background-color:#cccccc");
        }
    }

    public Dialog(boolean modal) {
        this.isModal = modal;

        this.setStyle("-fx-background-color:#ff0000");
        this.setPrefSize(300, 200);
        this.setManaged(false);

        if (isModal) {
            modalPane = new Pane();
            modalPane.setStyle("-fx-background-color:#cccccc");
        }

        topBar = new TopBar(this);
        this.setTop(topBar);
    }

    public void show() {
        if (isModal) {
            modalPane = new Pane();
            modalPane.setStyle("-fx-background-color:#333333");
            desktop.getChildren().add(modalPane);
        }
        desktop.getChildren().add(this);
        this.autosize();
    }

    public void close() {
        if (modalPane != null) {
            desktop.getChildren().remove(modalPane);
        }
        desktop.getChildren().remove(this);
    }

    public boolean isModal() {
        return isModal;
    }

    public void setModal(boolean modal) {
        isModal = modal;
    }

    public Pane getModalPane() {
        return modalPane;
    }

    public void setModalPane(Pane modalPane) {
        this.modalPane = modalPane;
    }

    class TopBar extends ToolBar {
        private double mouseDragOffsetX = 0;
        private double mouseDragOffsetY = 0;

        public TopBar(final Dialog dialog) {
            this.setId("banner");
            if (!SysInfo.isApplet) {
                // add close min max
                final WindowButtons windowButtons = new WindowButtons(dialog);
                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                this.getItems().addAll(region, windowButtons);
                // add window header double clicking
                this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            windowButtons.toogleMaximized();
                        }
                    }
                });
                // add window dragging
                this.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent event) {
                        mouseDragOffsetX = event.getSceneX();
                        mouseDragOffsetY = event.getSceneY();
                    }
                });
                this.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent event) {
                        if(!windowButtons.isMaximized()) {
                            dialog.layoutXProperty().unbind();
                            dialog.layoutYProperty().unbind();
                            System.out.println("screenX = " + event.getSceneX() + " screenY = " + event.getSceneY());
                            System.out.println("mouseDX = " + mouseDragOffsetX + " mouseDY = " + mouseDragOffsetY);
                            System.out.println("x = " + (event.getScreenX() - mouseDragOffsetX) + " y = " + (event.getScreenY() - mouseDragOffsetY));
                            dialog.setLayoutX(event.getScreenX() - mouseDragOffsetX);
                            dialog.setLayoutY(event.getScreenY() - mouseDragOffsetY);
                        }
                    }
                });
            }
        }
    }
}
