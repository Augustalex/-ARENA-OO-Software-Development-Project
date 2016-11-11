package views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow/MainWindowView.fxml"));
        Parent parent = loader.load();

        FXMLViewController controller = loader.getController();

        primaryStage.setTitle("ARENA");
        primaryStage.setScene(new Scene(parent, 1200, 900));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> controller.closeView());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
