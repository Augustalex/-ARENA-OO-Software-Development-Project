package views.PlayView;

import advertisement.ad.*;
import advertisement.adDisplay.AdDisplayFactory;
import advertisement.adDisplay.PaneAdDisplay;
import advertisement.adRepository.AdRepository;
import advertisement.adRepository.AdRepositoryFactory;
import advertisement.adPreference.AdPreferenceFactory;
import advertisement.adSpot.AdSpot;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import gameInformation.GameInformation;
import gameInformation.OthelloGameInformation;
import views.AdvertiserView;
import views.FXMLViewController;
import views.GameInformationView.GameInformationViewController;
import views.DimensionBinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * View controller for the "Play" view. Sets up the game choices available, and sets
 * up routing for buttons to their corresponding "GameInformation" views.
 *
 * Might also set up Ads for when the view is loaded. (This is because Ads in this view
 * is not necessarily a permanent design decision).
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


        DimensionBinder.bindWidthToPercentageOfContainer(gameInformationContainer, 0.9, playViewWindow);
        DimensionBinder.bindWidthToPercentageOfContainer(gameList, 0.1, playViewWindow);
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
//        adPreferenceQueue.getAdsQueue().submit(fxAd);
//        adPreferenceQueue.getAdsQueue().submit(fxAd2);
//        adPreferenceQueue.getAdsQueue().submit(fxAd3);
//        adPreferenceQueue.getAdsQueue().submit(fxAd6);
//        adPreferenceQueue.getAdsQueue().submit(fxAd4);
//        adPreferenceQueue.getAdsQueue().submit(fxAd5);

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
                .newPaneAdDisplay(adSpot, this.advertBottom)
                .setAsWideAd();

        this.paneAdDisplay.showAd();
        this.paneAdDisplay.startAdLoop(2000);
    }

    private void setupAdSpot(StackPane advert){
        DimensionBinder.bindHeightToPercentageOfContainer(advert, 0.35, playViewWindow);
        DimensionBinder.bindWidthToPercentageOfContainer(advert, 1, playViewWindow);
    }

    private void setGameListButtonActions(){
        othelloGameButton.setOnAction(e -> {
            try{
                setGameInformationView(
                        (Region) loadGameInformationView(new OthelloGameInformation()),
                        gameInformationContainer
                );
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        });
    }

    private void setGameInformationView(Region newInformationView, Pane container){
        DimensionBinder.fixedBindTo(newInformationView, container);
        container.getChildren().setAll(newInformationView);
    }

    private Parent loadGameInformationView(GameInformation gameInformation) throws IOException {
        return
                this.loadFXML(
                "GameInformationView/GameInformationView.fxml",
                c -> new GameInformationViewController(gameInformation)
        );
    }

    private void bindListButtons(Pane list){
        for(Button button : list.getChildren().stream()
                .filter(node -> node instanceof Button).toArray(Button[]::new))
            DimensionBinder.bindWidthToPercentageOfContainer(button, 1, list);
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
