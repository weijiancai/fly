package com.fly.fxsys.control.valuectl;

import com.fly.fxsys.control.IValue;
import javafx.scene.control.TextArea;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class VTextArea extends TextArea implements IValue {
    @Override
    public String value() {
        return getText().trim();
    }

    @Override
    public void setValue(String value) {
        setText(value);
    }
}
