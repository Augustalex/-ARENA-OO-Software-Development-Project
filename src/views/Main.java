package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow/MainWindowView.fxml"));
        Parent parent = loader.load();

        //Loading controller to enable closing it on exit of application.
        FXMLViewController controller = loader.getController();

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("ARENA");
        primaryStage.setScene(new Scene(parent, 1600, 1000));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> controller.closeView());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
