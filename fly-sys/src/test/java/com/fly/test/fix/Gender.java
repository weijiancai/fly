package com.fly.test.fix;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public enum Gender {
    MALE(true),
    FEMALE (false);
    private boolean value;
    Gender(boolean _value){
        value = _value;
    }
}
