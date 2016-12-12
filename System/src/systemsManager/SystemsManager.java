package systemsManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by August on 2016-12-12.
 */
public class SystemsManager extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox container = new VBox();

        setupStage(primaryStage, container);
    }

    private void setupStage(Stage primaryStage, Pane container){
        primaryStage.setTitle("Systems Manager");
        primaryStage.setScene(new Scene(container, 400,1000));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
