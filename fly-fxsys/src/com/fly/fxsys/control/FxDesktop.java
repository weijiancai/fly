package com.fly.fxsys.control;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author weijiancai
 */
public class FxDesktop extends BorderPane {
    private Banner banner;
    private Workbench workbench;
    private ModuleMenu moduleMenu;

    public FxDesktop(final Stage stage) {
        banner = new Banner(stage);
        this.setTop(banner);
        moduleMenu = new ModuleMenu(this);
        this.setLeft(moduleMenu);
        workbench = new Workbench();
        this.setCenter(workbench);
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

    public ModuleMenu getModuleMenu() {
        return moduleMenu;
    }

    public void setModuleMenu(ModuleMenu moduleMenu) {
        this.moduleMenu = moduleMenu;
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Workbench workbench) {
        this.workbench = workbench;
    }
}
