package advertisement.adPlacer;

import advertisement.Ad;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by August on 2016-11-03.
 */
public class AdPlacer {

    private Pane container;
    private Queue<Ad> adsQueue = new LinkedBlockingQueue<>();

    private boolean exitLoop = false;

    public AdPlacer(Pane container){
        this.container = container;
    }

    public Queue<Ad> getAdsQueue(){
        return this.adsQueue;
    }

    public void displayNextAd(){
        if(this.adsQueue.size() < 1)
            return;

        Ad currentAd = this.adsQueue.remove();
        currentAd.getAdvertSpot().setVisible(true);
        this.container.getChildren().setAll(currentAd.getAdvertSpot());

        this.adsQueue.add(currentAd);
    }

    public void loopAds(int timeIntervalMillis){
        this.exitLoop = false;
        new Timer().scheduleAtFixedRate(new AutoAdPlacer(this), 0, timeIntervalMillis);
    }

    public void cancelPossibleLoop(){
        this.exitLoop = true;
    }

    public boolean shouldExitLoop(){
        return this.exitLoop;
    }
}
