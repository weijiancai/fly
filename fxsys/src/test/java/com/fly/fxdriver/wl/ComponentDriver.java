package com.fly.fxdriver.wl;

import com.fly.fxdriver.wl.guesture.GesturePerformer;
import org.hamcrest.Matcher;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public abstract class ComponentDriver<T> {
    private final Prober prober;
    private final ComponentSelector<T> selector;

    protected final GesturePerformer gesturePerformer;

    public ComponentDriver() {
        this(null, null, null);
    }

    public ComponentDriver(GesturePerformer gesturePerformer, ComponentSelector<T> selector, Prober prober) {
        this.gesturePerformer = gesturePerformer;
        this.selector = selector;
        this.prober = prober;
    }

    /**
     * Check a probe.
     * <p/>
     * This is the fundamental hook for assertions and manipulations, upon which more convenient methods are
     * built.  It is exposed as a public method to act as an "escape route" so that
     * users can extend drivers through a stable extension point.
     *
     * @param probe The probe to be run and checked.
     */
    public void check(Probe probe) {
        prober.check(probe);
    }

    /**
     * Returns a selector that identifies the component driven by this driver.  This can be used to make assertions
     * about the component or search for subcomponents.
     *
     * @return a selector that identifies the component driven by this driver.
     * @see #check(com.objogate.wl.Probe)
     * @see #all(Class,org.hamcrest.Matcher)
     * @see #the(Class,org.hamcrest.Matcher)
     * @see #is(org.hamcrest.Matcher)
     * @see #has(com.objogate.wl.Query,org.hamcrest.Matcher)
     */
    public ComponentSelector<T> component() {
        return selector;
    }

    public <P> void has(Query<? super T, P> query, Matcher<? super P> criteria) {
//        check(new ComponentPropertyAssertionProbe<T, P>(selector, query, criteria));
    }
}
