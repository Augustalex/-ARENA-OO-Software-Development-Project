package liveStream;

/**
 * A public pre defaulted configuration file for the LiveStream class.
 */
public class LiveStreamConfiguration {

    /**
     * In milliseconds
     * 16 -> 60fps
     * 33 -> 30fps
     * 66 -> 15fps
     * 120 -> 8fps
     */
    public int broadcastInterval = 120;

    /**
     * How many viewers that are supported at one time.
     */
    public int viewersCapacity = 1000;

    /**
     * At what scale the image should be broadcast at.
     * 
     * Lower scale decreases the amount of data put on the sockets by a lot, but
     * will decrease the quality of the image dramatically.
     *
     * 1 is equal to not scaled at all.
     * 0.5 is equal to scaled to half of original size.
     */
    public double downscaleFactor = 1;

    /**
     * Lossless compression set to true will put less data on the
     * sockets. But will increase load on Server CPU.
     */
    public boolean losslessCompression = true;

    public LiveStreamConfiguration broadcastInterval(int interval){
        this.broadcastInterval = interval;
        return this;
    }

    public LiveStreamConfiguration viewersCapacity(int capacity){
        this.viewersCapacity = capacity;
        return this;
    }

    public LiveStreamConfiguration downscaleFactor(double factor){
        this.downscaleFactor = factor;
        return this;
    }

    public LiveStreamConfiguration losslessCompression(boolean toggle){
        this.losslessCompression = toggle;
        return this;
    }
}
