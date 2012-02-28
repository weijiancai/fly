import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author weijiancai
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        group.getChildren().add(new Label("zhang san feng"));
        Scene scene = new Scene(group, 300, 200);
        stage.setScene(scene);
        stage.setTitle("测试");
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
