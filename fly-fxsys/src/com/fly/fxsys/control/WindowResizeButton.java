package com.fly.fxsys.control;

import com.fly.fxsys.R;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Simple draggable area for the bottom right of a window to support resizing.
 *
 * @author weijiancai
 */
public class WindowResizeButton extends Region {
    private double dragOffsetX, dragOffsetY;

    public WindowResizeButton(final Stage stage, final double stageMinimumWidth, final double stageMinimumHeight) {
        setId(R.id.WINDOW_RESIZE_BUTTON);
        setPrefSize(11,11);
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                dragOffsetX = (stage.getX() + stage.getWidth()) - e.getScreenX();
                dragOffsetY = (stage.getY() + stage.getHeight()) - e.getScreenY();
                e.consume();
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                ObservableList<Screen> screens = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1);
                final Screen screen;
                if(screens.size()>0) {
                    screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
                }
                else {
                    screen = Screen.getScreensForRectangle(0,0,1,1).get(0);
                }
                Rectangle2D visualBounds = screen.getVisualBounds();
                double maxX = Math.min(visualBounds.getMaxX(), e.getScreenX() + dragOffsetX);
                double maxY = Math.min(visualBounds.getMaxY(), e.getScreenY() - dragOffsetY);
                stage.setWidth(Math.max(stageMinimumWidth, maxX - stage.getX()));
                stage.setHeight(Math.max(stageMinimumHeight, maxY - stage.getY()));
                e.consume();
            }
        });
    }

    public WindowResizeButton(final Pane pane, final double stageMinimumWidth, final double stageMinimumHeight) {
        setId(R.id.WINDOW_RESIZE_BUTTON);
        setPrefSize(11,11);
        //this.setManaged(false);

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                dragOffsetX = (pane.getLayoutX() + pane.getWidth()) - e.getScreenX();
                dragOffsetY = (pane.getLayoutY() + pane.getHeight()) - e.getScreenY();
                e.consume();
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                ObservableList<Screen> screens = Screen.getScreensForRectangle(pane.getLayoutX(), pane.getLayoutY(), 1, 1);
                final Screen screen;
                if(screens.size()>0) {
                    screen = Screen.getScreensForRectangle(pane.getLayoutX(), pane.getLayoutY(), 1, 1).get(0);
                }
                else {
                    screen = Screen.getScreensForRectangle(0,0,1,1).get(0);
                }
                Rectangle2D visualBounds = screen.getVisualBounds();
                double maxX = Math.min(visualBounds.getMaxX(), e.getScreenX() + dragOffsetX);
                double maxY = Math.min(visualBounds.getMaxY(), e.getScreenY() - dragOffsetY);
                pane.maxWidth(Math.max(stageMinimumWidth, maxX - pane.getLayoutX()));
                pane.maxHeight(Math.max(stageMinimumHeight, maxY - pane.getLayoutY()));
                pane.autosize();
                e.consume();
            }
        });
    }
}
