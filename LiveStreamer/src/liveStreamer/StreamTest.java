package liveStreamer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by August on 2016-11-28.
 */
public class StreamTest extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/liveStreamer/MainView.fxml"));
        System.out.println("Loading pane.");
        Pane pane = loader.load();
        primaryStage.setScene(new Scene(pane, 1000, 1000));
        primaryStage.show();
        System.out.println("Stage shown.");
    }

    public static void main(String[] args){
        launch(args);
    }
}
