package serviceClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by August on 2016-12-12.
 */
public class SystemsManager extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/serviceClient/views/mainWindow/MainWindow.fxml"));
            Pane main = loader.load();
            setupStage(primaryStage, main);
        }
        catch(Exception ex){
            System.out.println("Could not load main window!");
            ex.printStackTrace();
        }
    }

    private void setupStage(Stage primaryStage, Pane container){
        primaryStage.setTitle("Systems Manager");
        primaryStage.setScene(new Scene(container, 500,800));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
