package views.PlayView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Window;
import model.GameInformation;
import model.OthelloGameInformation;
import views.AdvertSpot.AdvertHideButton;
import views.AdvertSpot.AdvertSpot;
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
    private Button othelloGameButton;

    @FXML
    private VBox gameList;

    @FXML
    private AdvertSpot advertBottom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ViewDimensionBinder.bindWidthToPercentageOfContainer(gameInformationContainer, 0.9, playViewWindow);
        ViewDimensionBinder.bindWidthToPercentageOfContainer(gameList, 0.1, playViewWindow);
        bindListButtons(gameList);

        setGameListButtonActions();

        setupAdSpot(advertBottom);
        advertBottom.placeAd(new Background(new BackgroundImage(
                new Image("/coke.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        )));

        advertBottom.toFront();

        AdvertHideButton hideButton = new AdvertHideButton();
        hideButton.stylize();
        hideButton.place(advertBottom);
        hideButton.setHideOnAction();
    }

    private void setupAdSpot(AdvertSpot advert){
        ViewDimensionBinder.bindHeightToPercentageOfContainer(advert, 0.2, playViewWindow);
        ViewDimensionBinder.bindWidthToPercentageOfContainer(advert, 1, playViewWindow);
    }

    private void setGameListButtonActions(){
        othelloGameButton.setOnAction(e -> {
            try{
                fillGameInformationViewWithNewView(
                        loadGameInformationView(new OthelloGameInformation()),
                        gameInformationContainer
                );
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        });
    }

    private void fillGameInformationViewWithNewView(Pane newInformationView, Pane container){
        ViewDimensionBinder.fixedBindTo(newInformationView, container);
        container.getChildren().setAll(newInformationView);
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
