package advertisement.ad;

import javafx.scene.image.Image;

/**
 * Implementation of Ad with JavaFX Image class.
 */
public class ImageAd implements Ad<Image>{

    private final Image image;

    public ImageAd(String imageURL){
        this.image = new Image(imageURL);
    }

    @Override
    public Image getContent() {
        return this.image;
    }
}
