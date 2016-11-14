package serviceCenter.tournamentConfigurationReviewer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Patric on 2016-11-14.
 */
public abstract class ReviewerQueue<T extends Reviewer> {

    private Queue<T> reviewerQueue = new LinkedBlockingQueue<>();

    public ReviewerQueue(){

    }

    public void addReviewer(Reviewer reviewer){
    }

}
