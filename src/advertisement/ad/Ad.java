package advertisement.ad;

import javafx.scene.image.Image;

/**
 * Ad for use in AdSpots and AdDisplays.
 */
public interface Ad {

    /**
     * Returns JavaFX Image object that represents the Ad.
     * @return
     */
    Image getImage();
}
