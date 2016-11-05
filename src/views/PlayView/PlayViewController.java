package views.PlayView;

import advertisement.Ad;
import advertisement.adPlacer.AdPlacer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import model.GameInformation;
import model.OthelloGameInformation;
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
    private StackPane advertBottom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ViewDimensionBinder.bindWidthToPercentageOfContainer(gameInformationContainer, 0.9, playViewWindow);
        ViewDimensionBinder.bindWidthToPercentageOfContainer(gameList, 0.1, playViewWindow);
        bindListButtons(gameList);

        setGameListButtonActions();

        setupAdSpot(advertBottom);
        advertBottom.toFront();

        Ad ad = new Ad("/coke.jpg");
        ad.setAsWideAdvert();

        Ad ad2 = new Ad("/adImages/koolaid.gif");
        ad2.setAsWideAdvert();

        Ad ad3 = new Ad("/adImages/nintendo.gif");
        ad3.setAsWideAdvert();

        Ad ad4 = new Ad("/adImages/gameboy.gif");
        ad4.setAsWideAdvert();

        Ad ad5 = new Ad("/adImages/caprisun.gif");
        ad5.setAsWideAdvert();

        Ad ad6 = new Ad("/adImages/kawasaki.gif");
        ad6.setAsWideAdvert();

        AdPlacer adPlacer = new AdPlacer(advertBottom);
        adPlacer.getAdsQueue().add(ad);
        adPlacer.getAdsQueue().add(ad2);
        adPlacer.getAdsQueue().add(ad3);
        adPlacer.getAdsQueue().add(ad6);
        adPlacer.getAdsQueue().add(ad4);
        adPlacer.getAdsQueue().add(ad5);

        adPlacer.loopAds(2500);
    }

    private void setupAdSpot(StackPane advert){
        ViewDimensionBinder.bindHeightToPercentageOfContainer(advert, 0.35, playViewWindow);
        ViewDimensionBinder.bindWidthToPercentageOfContainer(advert, 1, playViewWindow);
    }

    private void setGameListButtonActions(){
        othelloGameButton.setOnAction(e -> {
            try{
                setGameInformationView(
                        loadGameInformationView(new OthelloGameInformation()),
                        gameInformationContainer
                );
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        });
    }

    private void setGameInformationView(Pane newInformationView, Pane container){
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
