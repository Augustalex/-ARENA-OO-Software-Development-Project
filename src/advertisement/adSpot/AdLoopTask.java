package advertisement.adSpot;

import java.util.TimerTask;

/**
 * Loops all Ads in an AdSpot when run as a Scheduled Task (from Timer class).
 */
public class AdLoopTask extends TimerTask {

    private final AdSpot adspot;

    public AdLoopTask(AdSpot adSpot){
        this.adspot = adSpot;
    }

    @Override
    public void run() {

        this.adspot.nextAd();
    }
}
