package views.PlayView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.GameInformation;
import model.OthelloGameInformation;
import tests.RunMatch;
import views.GameInformationView.GameInformationViewController;
import views.ViewDimensionBinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-11-02.
 */
public class PlayViewController implements Initializable{

    @FXML
    private BorderPane playViewWindow;

    @FXML
    private StackPane gameInformationContainer;

    @FXML
    private StackPane advertBottom;

    @FXML
    private Button othelloGameButton;

    @FXML
    private VBox gameList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ViewDimensionBinder.bindWidthToPercentageOfContainer(gameInformationContainer, 0.9, playViewWindow);
        ViewDimensionBinder.bindWidthToPercentageOfContainer(gameList, 0.1, playViewWindow);
        bindListButtons(gameList);


        advertBottom.prefWidthProperty().bind(playViewWindow.widthProperty());
        advertBottom.prefHeightProperty().bind(playViewWindow.heightProperty().multiply(0.2));
        advertBottom.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        othelloGameButton.setOnAction(e -> {
            try {
                Pane pane = loadGameInformationView(new OthelloGameInformation());
                System.out.println(pane);
                ViewDimensionBinder.fixedBindTo(pane, gameInformationContainer);
                gameInformationContainer.getChildren().setAll(pane);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

    }

    private Pane loadGameInformationView(GameInformation gameInformation) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameInformationView/GameInformationView.fxml"));

        loader.setControllerFactory(c -> new GameInformationViewController(gameInformation));

        return loader.load();
    }

    private void bindListButtons(Pane list){
        for(Button button : list.getChildren().stream().filter(node -> node instanceof Button).toArray(Button[]::new))
            ViewDimensionBinder.bindWidthToPercentageOfContainer(button, 1, list);
    }

}
