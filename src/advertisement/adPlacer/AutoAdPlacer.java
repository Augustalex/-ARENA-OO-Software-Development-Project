package advertisement.adPlacer;

import javafx.application.Platform;

import java.util.TimerTask;

/**
 * Created by August on 2016-11-03.
 */
public class AutoAdPlacer extends TimerTask {

    private AdPlacer adPlacer;

    public AutoAdPlacer(AdPlacer adPlacer){
        this.adPlacer = adPlacer;
    }

    @Override
    public void run() {
        if(this.adPlacer.shouldExitLoop())
            this.cancel();
        else
            Platform.runLater(() -> this.adPlacer.displayNextAd());
    }
}
