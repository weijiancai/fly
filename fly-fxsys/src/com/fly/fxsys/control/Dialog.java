package com.fly.fxsys.control;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author weijiancai
 */
public class Dialog extends StackPane {
    private FxDesktop desktop;
    private boolean isModal;
    private Pane modalPane;

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
}
