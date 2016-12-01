package arena.advertisement.advertisement.adSpot;

import arena.advertisement.advertisement.ad.Ad;
import arena.advertisement.advertisement.adQueue.AdQueue;
import arena.advertisement.advertisement.adQueue.AdQueueFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;

/**
 * Implements AdSpot with an AdQueue.
 */
public class QueueAdSpot implements AdSpot {

    private final ObjectProperty<Ad> currentAd = new SimpleObjectProperty<>(null);

    private final BooleanProperty cancelLoop = new SimpleBooleanProperty(false);

    private final AdQueue<Ad> queue;

    public QueueAdSpot(Ad[] ads){
        this.queue = new AdQueueFactory<>().newAdQueue();

        Arrays.stream(ads)
                .forEach(ad -> this.queue.addAd(ad));
    }

    @Override
    public void setAd(Ad ad) {
        this.currentAd.set(ad);
    }

    @Override
    public void nextAd() {
        this.currentAd.set(this.queue.dequeue());
        this.queue.addAd(this.currentAd.get());
    }

    @Override
    public void loopAds(long timePerAdInMilliseconds) {
        this.getLoopCancelProperty().set(false);

        AdLooper looper = new AdLooper(this);
        looper.start(timePerAdInMilliseconds);

        this.getLoopCancelProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue)
                looper.stop();
        }));
    }

    @Override
    public BooleanProperty getLoopCancelProperty() {
        return this.cancelLoop;
    }

    @Override
    public ObjectProperty<? extends Ad> currentAdProperty() {
        return this.currentAd;
    }

    @Override
    public AdQueue getAdQueue() {
        return this.queue;
    }
}
