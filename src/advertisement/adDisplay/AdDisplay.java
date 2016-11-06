package advertisement.adDisplay;

import advertisement.adSpot.AdSpot;

/**
 * Created by August on 2016-11-05.
 */
public interface AdDisplay {

    AdDisplay setAdSpot(AdSpot adSpot);

    AdDisplay setAsWideAd();

    AdDisplay setAsTallAd();

    void hideAd();

    void showAd();

    void hideCloseButton();

    void showCloseButton();
}
