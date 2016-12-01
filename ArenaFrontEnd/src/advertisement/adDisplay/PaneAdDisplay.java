package advertisement.adDisplay;

import arena.advertisement.advertisement.ad.Ad;
import arena.advertisement.advertisement.adSpot.AdSpot;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * An abstract class for representing the Interface of concrete
 * JavaFX AdDisplays.
 */
public abstract class PaneAdDisplay extends BorderPane implements AdDisplay{

    protected AdSpot adSpot;
    private Pane container;

    public PaneAdDisplay(AdSpot adSpot, Pane container){
        this.container = container;
        this.setAdSpot(adSpot);

        this.setId("testAd");
    }

    /**
     * Starts the loop in the AdSpot of the current AdDisplay.
     * @param intervalInMilliseconds
     */
    public void startAdLoop(int intervalInMilliseconds){
        this.adSpot.loopAds(intervalInMilliseconds);
    }

    /**
     * Closes loop (will work even if loop is not active).
     */
    public void closeLoop(){
        this.adSpot.getLoopCancelProperty().set(true);
    }

    /**
     * Displays given ads contents in the PaneAdDisplay.
     * @param ad
     */
    protected abstract void displayAdContent(Ad<Image> ad);

    @Override
    public AdDisplay setAdSpot(AdSpot adSpot) {
        this.adSpot = adSpot;

        return this;
    }

    @Override
    public void hideAd() {
        this.container.getChildren().remove(this);
        this.setMouseTransparent(true);
    }

    @Override
    public void showAd() {
        this.container.getChildren().add(this);
    }

    /**
     * Starts a listener that displays new Ad content when the
     * current Ad of the AdSpot changes.
     */
    protected void listenToCurrentAdChanges(){
        this.adSpot.currentAdProperty().addListener((observable, oldValue, newValue) -> displayAdContent(newValue));
    }

}
