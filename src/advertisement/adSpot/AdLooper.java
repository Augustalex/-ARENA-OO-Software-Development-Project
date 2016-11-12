package advertisement.adSpot;

import java.util.Timer;

/**
 * Loops ads in AdSpot from start until stop is run.
 */
public class AdLooper {

    private final Timer timer;
    private final AdSpot adSpot;

    public AdLooper(AdSpot adSpot){
        this.adSpot = adSpot;
        this.timer = new Timer();
    }

    /**
     * Starts a loop that shifts to next ad at a fixed rate.
     * @param timePerAdInMilliseconds
     */
    public void start(long timePerAdInMilliseconds){
        this.timer.scheduleAtFixedRate(new AdLoopTask(this.adSpot), 0, timePerAdInMilliseconds);
    }

    /**
     * Stops the loop.
     */
    public void stop(){
        this.timer.cancel();
    }
}
