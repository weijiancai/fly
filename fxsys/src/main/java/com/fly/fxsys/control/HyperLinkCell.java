package com.fly.fxsys.control;

import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

/**
 * @author weijiancai
 */
public class HyperlinkCell extends TableCell<Map<String, Object>, String> {
    private Hyperlink hyperlink;

    public HyperlinkCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (hyperlink == null) {
            createTextField();
        }
        setGraphic(hyperlink);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(getItem());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (hyperlink != null) {
                    hyperlink.setText(getString());
                }
                setGraphic(hyperlink);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    private void createTextField() {
        hyperlink = new Hyperlink(getString());
        hyperlink.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        hyperlink.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(hyperlink.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem();
    }
}
