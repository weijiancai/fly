package com.fly.base.util;

/**
 * @author weijiancai
 */
public interface Callback<T> {
    void call(T t, Object... obj) throws Exception;
}
