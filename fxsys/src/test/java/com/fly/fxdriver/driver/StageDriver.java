package com.fly.fxdriver.driver;

import com.fly.fxdriver.wl.ComponentDriver;
import com.fly.fxdriver.wl.Query;
import javafx.stage.Stage;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class StageDriver extends ComponentDriver<Stage> {
    public void hasTitle(String title) {
        hasTitle(equalTo(title));
    }

    private void hasTitle(Matcher<String> matcher) {
        has(title(), matcher);
    }

    private static Query<Stage, String> title() {
        return new Query<Stage, String>() {
            @Override
            public String query(Stage stage) {
                return stage.getTitle();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("stage title");
            }
        };
    }
}
