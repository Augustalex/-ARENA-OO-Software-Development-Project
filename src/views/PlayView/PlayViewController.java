package views.PlayView;

import advertisement.ad.*;
import advertisement.adDisplay.AdDisplay;
import advertisement.adDisplay.AdDisplayFactory;
import advertisement.adDisplay.PaneAdDisplay;
import advertisement.adPlacer.AdRepository;
import advertisement.adPlacer.AdRepositoryFactory;
import advertisement.adPlacer.QueueAdRepository;
import advertisement.adPreference.AdPreferenceFactory;
import advertisement.adSpot.AdSpot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.GameInformation;
import model.OthelloGameInformation;
import views.AdvertiserView;
import views.FXMLViewController;
import views.GameInformationView.GameInformationViewController;
import views.ViewDimensionBinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-11-02.
 */
public class PlayViewController extends FXMLViewController implements AdvertiserView{

    private PaneAdDisplay paneAdDisplay = null;

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


//
//
//        FxAd fxAd = new FxAd("/coke.jpg");
//        fxAd.setAsWideAdvert();
//
//        FxAd fxAd2 = new FxAd("/adImages/koolaid.gif");
//        fxAd2.setAsWideAdvert();
//
//        FxAd fxAd3 = new FxAd("/adImages/nintendo.gif");
//        fxAd3.setAsWideAdvert();
//
//        FxAd fxAd4 = new FxAd("/adImages/gameboy.gif");
//        fxAd4.setAsWideAdvert();
//
//        FxAd fxAd5 = new FxAd("/adImages/caprisun.gif");
//        fxAd5.setAsWideAdvert();
//
//        FxAd fxAd6 = new FxAd("/adImages/kawasaki.gif");
//        fxAd6.setAsWideAdvert();
//
//        AdPreferenceQueue adPreferenceQueue = new AdPreferenceQueue(advertBottom);
//        adPreferenceQueue.getAdsQueue().add(fxAd);
//        adPreferenceQueue.getAdsQueue().add(fxAd2);
//        adPreferenceQueue.getAdsQueue().add(fxAd3);
//        adPreferenceQueue.getAdsQueue().add(fxAd6);
//        adPreferenceQueue.getAdsQueue().add(fxAd4);
//        adPreferenceQueue.getAdsQueue().add(fxAd5);

//        adPreferenceQueue.loopAds(2500);

        AdRepository adRepository = AdRepositoryFactory.newAdRepository();

        adRepository
            .addPreferredAd(PreferredAdFactory.newPlayerViewPreferredAd("/adImages/koolaid.gif"))
            .addPreferredAd(PreferredAdFactory.newPlayerViewPreferredAd("/adImages/nintendo.gif"))
            .addPreferredAd(PreferredAdFactory.newPlayerViewPreferredAd("/adImages/gameboy.gif"))
            .addPreferredAd(PreferredAdFactory.newPlayerViewPreferredAd("/adImages/caprisun.gif"))
            .addPreferredAd(PreferredAdFactory.newPlayerViewPreferredAd("/adImages/kawasaki.gif"))
        ;

        AdSpot adSpot = adRepository.newAdSpot(AdPreferenceFactory.newPlayViewPreference());

        this.paneAdDisplay = (PaneAdDisplay) AdDisplayFactory
                .newPaneAdDisplay(adSpot)
                .setAsWideAd();

        this.advertBottom.getChildren().add(this.paneAdDisplay);

        this.paneAdDisplay.startAdLoop(2000);
    }

    private void setupAdSpot(StackPane advert){
        ViewDimensionBinder.bindHeightToPercentageOfContainer(advert, 0.35, playViewWindow);
        ViewDimensionBinder.bindWidthToPercentageOfContainer(advert, 1, playViewWindow);
    }

    private void setGameListButtonActions(){
        othelloGameButton.setOnAction(e -> {
            try{
                setGameInformationView(
                        (Pane) loadGameInformationView(new OthelloGameInformation()),
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

    private Parent loadGameInformationView(GameInformation gameInformation) throws IOException {
        return loadFXML(
                "/views/GameInformationView/GameInformationView.fxml",
                c -> new GameInformationViewController(gameInformation)
        );
    }

    private void bindListButtons(Pane list){
        for(Button button : list.getChildren().stream()
                .filter(node -> node instanceof Button).toArray(Button[]::new))
            ViewDimensionBinder.bindWidthToPercentageOfContainer(button, 1, list);
    }

    @Override
    public void closeView() {
        this.closeAds();
    }

    @Override
    public void closeAds() {
        this.paneAdDisplay.hideAd();
        this.paneAdDisplay.closeLoop();
    }
}
