package com.fly.base.db.util;

/**
 * @author weijiancai
 */
public interface Callback<T> {
    void call(T t, Object... obj);
}
