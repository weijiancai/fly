package com.fly.fxsys.control.valuectl;

import com.fly.fxsys.control.IValue;
import javafx.scene.control.PasswordField;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class VPasswordField extends PasswordField implements IValue {
    @Override
    public String value() {
        return getText().trim();
    }

    @Override
    public void setValue(String value) {
        setText(value);
    }
}
