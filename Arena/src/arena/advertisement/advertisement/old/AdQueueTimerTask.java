package arena.advertisement.advertisement.old;

import javafx.application.Platform;

import java.util.TimerTask;

/**
 * A TimerTask for looping images in an AdQueue.
 */
public class AdQueueTimerTask extends TimerTask {

    private final AdPreferenceQueue adPreferenceQueue;

    public AdQueueTimerTask(AdPreferenceQueue adPreferenceQueue){
        this.adPreferenceQueue = adPreferenceQueue;
    }

    @Override
    public void run() {
        if(this.adPreferenceQueue.shouldExitLoop())
            this.cancel();
        else
            Platform.runLater(() -> this.adPreferenceQueue.displayNextAd());
    }
}
