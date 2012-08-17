package com.fly.fxsys.control.valuectl;

import com.fly.fxsys.control.IValue;
import javafx.scene.control.TextField;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class VTextField extends TextField implements IValue {
    @Override
    public String[] values() {
        return new String[]{value()};
    }

    @Override
    public String value() {
        return getText().trim();
    }

    @Override
    public void setValue(String[] value) {
        if (value != null && value.length > 0) {
            setText(value[0]);
        }
    }

    @Override
    public void setValue(String value) {
        setValue(new String[]{value});
    }
}
