package advertisement.old;

import javafx.application.Platform;

import java.util.TimerTask;

/**
 * Created by August on 2016-11-03.
 */
public class AdQueueTimerTask extends TimerTask {

    private AdPreferenceQueue adPreferenceQueue;

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
