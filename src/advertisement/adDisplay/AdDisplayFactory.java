package advertisement.adDisplay;

import advertisement.adSpot.AdSpot;

/**
 * Creates a new AdDisplay object from a concrete class and
 * hides it behind the Interface {@link AdDisplay}.
 */
public class AdDisplayFactory {

    public static AdDisplay newAdDisplay(AdSpot adSpot){
        return new FxAdDisplay(adSpot);
    }

    /**
     * Hides a concrete object behind the Abstract class of
     * PaneAdDisplay. Is thus usable as a view object within
     * JavaFX Panes.
     * @return
     */
    public static PaneAdDisplay newPaneAdDisplay(AdSpot adSpot){
        return new FxAdDisplay(adSpot);
    }
}
