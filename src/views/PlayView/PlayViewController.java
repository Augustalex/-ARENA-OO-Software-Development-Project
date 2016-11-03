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

        bindListDimensions(gameList, 0.1, playViewWindow);
        bindListButtons(gameList);

        advertBottom.prefWidthProperty().bind(playViewWindow.widthProperty());
        advertBottom.prefHeightProperty().bind(playViewWindow.heightProperty().multiply(0.2));
        advertBottom.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        othelloGameButton.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameInformationView/GameInformationView.fxml"));

            loader.setControllerFactory(c -> {
                GameInformation othelloInformation = new OthelloGameInformation();
                return new GameInformationViewController(othelloInformation);
            });
            try {
                Parent parent = loader.load();
                gameInformationContainer.getChildren().setAll(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    private void bindListDimensions(Pane list, double listWidthPercentage, Pane container){
        ViewDimensionBinder.bindOneToOneDimension(
                list.minWidthProperty(),
                list.maxWidthProperty(),
                container.widthProperty().multiply(listWidthPercentage)
        );
    }

    private void bindListButtons(Pane list){
        for(Button button : list.getChildren().stream().filter(node -> node instanceof Button).toArray(Button[]::new)){
            ViewDimensionBinder.bindOneToOneDimension(
                    button.minWidthProperty(),
                    button.maxWidthProperty(),
                    list.widthProperty()
            );
        }
    }
}
