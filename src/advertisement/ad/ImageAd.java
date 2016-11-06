package advertisement.ad;

import javafx.scene.image.Image;

/**
 * Implementation of Ad with JavaFX Image class.
 */
public class ImageAd implements Ad{

    private Image image;

    public ImageAd(String imageURL){
        this.image = new Image(imageURL);
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
