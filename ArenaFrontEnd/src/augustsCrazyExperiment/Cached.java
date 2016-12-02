package augustsCrazyExperiment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Wrapper that provides caching functionality to any Object T.
 *
 * It works by taking a {@link Supplier} as an argument which
 * aids in replenishing the cache once retired by the scheduled
 * cache invalidator.
 *
 * @param <T>
 */
public class Cached<T> {

    private T cache = null;
    private Supplier<T> cacheRevalidator;

    private long cacheRetireTime = 600000;

    private ScheduledExecutorService cacheInvalidator = Executors.newScheduledThreadPool(1);

    public Cached(Supplier<T> cacheRevalidator){
        this.cacheRevalidator = cacheRevalidator;
        this.cache = this.cacheRevalidator.get();

        startCacheInvalidator();
    }

    public Cached(Supplier<T> cacheRevalidator, long retireTime){
        this(cacheRevalidator);
        this.cacheRetireTime = retireTime;

    }

    /**
     * Returns the stored cache. If no cache is valid, the cache is
     * updated and then returned.
     * @return
     */
    public T get(){
        if(this.cache == null)
            return cacheAndGet();
        else
            return this.cache;
    }

    /**
     * Updates cache and returns the newly updated cache.
     * @return
     */
    private T cacheAndGet(){
        this.cache = this.cacheRevalidator.get();
        return this.cache;
    }

    /**
     * Starts a scheduler that invalidates the cache at a fixed rate.
     */
    public void startCacheInvalidator(){
        this.cacheInvalidator.schedule(() -> {
            this.invalidate();
        }, this.cacheRetireTime, TimeUnit.MILLISECONDS);
    }

    /**
     * Stops the cache invalidation scheduler.
     */
    public void stopCacheInvalidator(){
        this.cacheInvalidator.shutdown();
    }

    /**
     * Manually invalidates the cache.
     */
    public void invalidate(){
        this.cache = null;
    }
}
