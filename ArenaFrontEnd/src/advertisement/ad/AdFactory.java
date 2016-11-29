package advertisement.ad;

/**
 * Creates Ads from concrete classes and hides
 * them behind the interface {@link Ad}.
 */
public class AdFactory {

    public static Ad newAd(String imageURL){
        return new ImageAd(imageURL);
    }
}
