package com.fly.fxdriver.wl;

import org.hamcrest.SelfDescribing;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface Gesture extends SelfDescribing {
    void performGesture(Automaton automaton);
}
