package arena.advertisement.ad;

/**
 * Ad for use in AdSpots and AdDisplays.
 */
public interface Ad<T> {

    /**
     * Returns JavaFX Image object that represents the Ad.
     * @return
     */
    T getContent();
}
