import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import session.Session;
import users.Player;
import views.FXMLViewController;

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
    }

    public static void main(String[] args) {
        Session.getSession().setPlayer(Player.newMockPlayer());
        launch(args);
    }
}
