package com.fly.fxdriver.wl;

/**
 * Wanted to have an interface so that more complex interactions can be tested without using a real awt robot
 *
 * @author weijiancai
 * @version 1.0.0
 */
public interface Automaton {
    /**
     * Performs all the given gestures on this Automaton.
     *
     * @param gestures the gestures to perform.
     */
    void perform(Gesture... gestures);
}
