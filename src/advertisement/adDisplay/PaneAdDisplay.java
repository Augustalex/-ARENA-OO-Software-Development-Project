package advertisement.adDisplay;

import advertisement.ad.Ad;
import advertisement.adDisplay.exceptions.CancelOnRougePane;
import advertisement.adSpot.AdSpot;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * An abstract class for representing the Interface of concrete
 * JavaFX AdDisplays.
 */
public abstract class PaneAdDisplay extends BorderPane implements AdDisplay{

    protected AdSpot adSpot;

    public PaneAdDisplay(AdSpot adSpot){
        this.setAdSpot(adSpot);
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
    protected abstract void displayAdContent(Ad ad);

    @Override
    public AdDisplay setAdSpot(AdSpot adSpot) {
        this.adSpot = adSpot;

        return this;
    }

    @Override
    public void hideAd() {
        this.setVisible(false);
    }

    @Override
    public void showAd() {
        this.setVisible(true);
    }

    /**
     * Starts a listener that displays new Ad content when the
     * current Ad of the AdSpot changes.
     */
    protected void listenToCurrentAdChanges(){
        this.adSpot.currentAdProperty().addListener((observable, oldValue, newValue) -> {
            displayAdContent(newValue);
        });
    }

}
