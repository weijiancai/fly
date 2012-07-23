package com.fly.fxdriver.wl;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface ComponentSelector<T> extends ComponentFinder<T> {
    T component();
}
