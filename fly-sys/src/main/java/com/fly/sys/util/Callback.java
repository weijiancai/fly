package com.fly.sys.util;

/**
 * @author weijiancai
 */
public interface Callback<T> {
    void call(T t, Object... obj);
}
