package advertisement.adDisplay;

import arena.advertisement.adSpot.AdSpot;
import javafx.scene.layout.Pane;

/**
 * Creates a new AdDisplay object from a concrete class and
 * hides it behind the Interface {@link AdDisplay}.
 */
public class AdDisplayFactory {

    public static AdDisplay newAdDisplay(AdSpot adSpot, Pane container){
        return new FxAdDisplay(adSpot, container);
    }

    /**
     * Hides a concrete object behind the Abstract class of
     * PaneAdDisplay. Is thus usable as a view object within
     * JavaFX Panes.
     * @return
     */
    public static PaneAdDisplay newPaneAdDisplay(AdSpot adSpot, Pane container){
        return new FxAdDisplay(adSpot, container);
    }
}
