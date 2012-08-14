package com.fly.fxsys.control.valuectl;

import com.fly.fxsys.control.IValue;
import javafx.scene.control.ComboBox;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class VComboBox extends ComboBox implements IValue {
    @Override
    public String value() {
        return this.getValue() == null ? "" : getValue().toString();
    }

    @Override
    public void setValue(String value) {
    }
}
