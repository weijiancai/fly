package com.fly.fxsys.control;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public interface IValue {
    String[] values();

    String value();

    void setValue(String[] value);

    void setValue(String value);
}
