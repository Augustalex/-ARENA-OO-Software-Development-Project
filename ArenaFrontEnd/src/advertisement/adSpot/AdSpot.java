package advertisement.adSpot;

import advertisement.ad.Ad;
import advertisement.adQueue.AdQueue;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

/**
 * Contains a set of ads that can be retrieved on notice
 * to display in a view. The AdSpot has the function to loop
 * through all containing ads with a specified millisecond
 * interval.
 */
public interface AdSpot {

    /**
     * Set the current Ad. (Does not add the Ad to the queue)
     * @param ad
     */
    void setAd(Ad ad);

    /**
     * Shifts to the next Ad in the queue.
     * The shifted Ad is moved to the end of the line.
     */
    void nextAd();

    /**
     * Loops all contained ads with a specified millisecond
     * interval.
     * @param timePerAdInMilliseconds
     */
    void loopAds(long timePerAdInMilliseconds);

    /**
     * When set to true, cancels current ongoing loops.
     * @return
     */
    BooleanProperty getLoopCancelProperty();

    /**
     * Updates to the latest ad, either by manually shifting ad,
     * or by a loop.
     * @return
     */
    ObjectProperty<? extends Ad> currentAdProperty();

    /**
     * Returns the contained queue of ads in the AdSpot.
     * @return
     */
    AdQueue getAdQueue();
}
