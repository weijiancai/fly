package com.fly.fxdriver.wl.guesture;

import com.fly.fxdriver.wl.Automaton;
import com.fly.fxdriver.wl.Gesture;
import com.fly.fxdriver.wl.robot.RoboticAutomaton;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class GesturePerformer {
    private final Automaton automaton;

    public GesturePerformer() {
        this(new RoboticAutomaton());
    }

    public GesturePerformer(Automaton automaton) {
        this.automaton = automaton;
    }

    public void perform(Gesture... gestures) {
        for (Gesture gesture : gestures) {
            gesture.performGesture(automaton);
        }
    }
}
