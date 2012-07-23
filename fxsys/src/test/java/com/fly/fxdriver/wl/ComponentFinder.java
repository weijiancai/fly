package com.fly.fxdriver.wl;

import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface ComponentFinder<T> extends Probe {
    List<T> components();
}
