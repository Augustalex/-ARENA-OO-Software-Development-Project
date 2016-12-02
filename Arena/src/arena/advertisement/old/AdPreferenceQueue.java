package arena.advertisement.old;

import arena.advertisement.ad.Ad;
import arena.advertisement.exceptions.NoAdsInQueue;

import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Uses the {@link Ad} class to display a list of ads.
 */
public class AdPreferenceQueue {

    private final Queue<Ad> adsQueue = new LinkedBlockingQueue<>();

    private boolean exitLoop = false;

    public Queue<Ad> getAdsQueue(){
        return this.adsQueue;
    }

    public Ad displayNextAd(){
        if(this.adsQueue.size() < 1)
            throw new NoAdsInQueue();

        Ad currentAd = this.adsQueue.remove();
       // currentAd.getAdvertSpot().setVisible(true);

        this.adsQueue.add(currentAd);

        return currentAd;
    }

    public void loopAds(int timeIntervalMillis){
        this.exitLoop = false;
        new Timer().scheduleAtFixedRate(new AdQueueTimerTask(this), 0, timeIntervalMillis);
    }

    public void cancelPossibleLoop(){
        this.exitLoop = true;
    }

    public boolean shouldExitLoop(){
        return this.exitLoop;
    }
}
