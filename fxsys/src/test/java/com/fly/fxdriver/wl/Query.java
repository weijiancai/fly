package com.fly.fxdriver.wl;

import org.hamcrest.SelfDescribing;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface Query<T, V> extends SelfDescribing {
    V query(T arg);
}
