package advertisement.adDisplay;

import advertisement.adSpot.AdSpot;

/**
 * A view object for displaying the controller object {@link AdSpot}.
 */
public interface AdDisplay {

    /**
     * Sets the current AdSpot for the display.
     *
     * There can only be one AdSpot attached at the same time.
     * @param adSpot
     * @return
     */
    AdDisplay setAdSpot(AdSpot adSpot);

    /**
     * Sets the dimensions of the ad to fit to the height of the
     * container and keep original ratio.
     * @return
     */
    AdDisplay setAsWideAd();

    /**
     * Sets the dimensions of the ad to fit to the width of the
     * container. Keeps the original ratio.
     * @return
     */
    AdDisplay setAsTallAd();

    /**
     * Hides the ad until explicitly told to unhide.
     */
    void hideAd();

    /**
     * "Unhides" the ad.
     */
    void showAd();

    /**
     * Hides the close button.
     */
    void hideCloseButton();

    /**
     * Shows the close button.
     */
    void showCloseButton();
}
