import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import arena.session.Session;
import arena.users.Player;
import views.FXMLViewController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow/MainWindowView.fxml"));
        Parent parent = loader.load();
        Platform.setImplicitExit(false);

        //Loading controller to enable closing it on exit of application.
        FXMLViewController controller = loader.getController();

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("ARENA");
        primaryStage.setScene(new Scene(parent, 1600, 1000));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            controller.closeView();
            primaryStage.close();
            Platform.exit();
        });

        startTestToolsView();
    }

    public static void main(String[] args) {

        //Session.getSession().setPlayer(Player.create());

        launch(args);

    }

    private void startTestToolsView(){
        try {
            Stage stage = new Stage(StageStyle.DECORATED);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/testTools/TestView.fxml"));
            Parent parent = loader.load();
            stage.setTitle("Test Tools");
            stage.setScene(new Scene(parent, 200, 650));
            stage.setX(10);
            stage.setY(10);
            stage.show();
        } catch (IOException e) {
            System.out.println("Could not start Test Tools View.");
            e.printStackTrace();
        }

    }
}
