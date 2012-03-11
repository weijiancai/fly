package com.fly.common;

/**
 * @author weijiancai
 */
public interface Callback<T> {
    void call(T t, Object... obj);
}
