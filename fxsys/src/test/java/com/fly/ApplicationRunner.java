package com.fly;

import com.fly.fxdriver.Probe;
import com.fly.fxsys.FxSys;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class ApplicationRunner extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        /*Group root = new Group();
        stage.setScene(new Scene(root));
        //create a button for initializing our new stage
        Button button = new Button("Create a Stage");
        button.setStyle("-fx-font-size: 24;");
        button.setDefaultButton(true);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                final Stage stage = new Stage();
                //create root node of scene, i.e. group
                Group rootGroup = new Group();
                //create scene with set width, height and color
                Scene scene = new Scene(rootGroup, 200, 200, Color.WHITESMOKE);
                //set scene to stage
                stage.setScene(scene);
                //center stage on screen
                stage.centerOnScreen();
                //show the stage
                stage.show();
                //add some node to scene
                Text text = new Text(20, 110, "JavaFX");
                text.setFill(Color.DODGERBLUE);
                text.setEffect(new Lighting());
                text.setFont(Font.font(Font.getDefault().getFamily(), 50));
                //add text to the main root group
                rootGroup.getChildren().add(text);
            }
        });
        root.getChildren().add(button);

        stage.show();*/
        System.out.println("test");
        FxSys fxSys = new FxSys();
        fxSys.start(stage);
        System.out.println(stage.getTitle());
        Probe probe = new Probe() {
            @Override
            public void probe() {
            }

            @Override
            public boolean isSatisfied() {
                return false;
            }

            @Override
            public void describeFailureTo(Description description) {
                description.appendText(" but is ").appendValue("aaaaaaaaaaaaaaaa");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("state title is ").appendValue(stage.getTitle());
            }
        };

        throw new AssertionError(describeFailureOf(probe));

//        Platform.exit();
    }

    protected String describeFailureOf(Probe probe) {
        StringDescription description = new StringDescription();

        description.appendText("\nTried to find:\n    ");
        probe.describeTo(description);
        description.appendText("\nbut:\n    ");
        probe.describeFailureTo(description);

        return description.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start() {
        launch();
    }

    public void startApp() {
        Thread thread = new Thread("Test Appication") {
            @Override
            public void run() {
                try {
                    ApplicationRunner.this.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Platform.runLater(thread);
    }

    public void exit() {
        Platform.exit();
    }
}
