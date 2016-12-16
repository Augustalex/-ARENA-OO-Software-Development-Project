package streamWatcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by August on 2016-11-28.
 */
public class MainWatcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/streamWatcher/Watcher.fxml"));
        Pane pane = loader.load();
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

//simon ip: 10.10.201.96