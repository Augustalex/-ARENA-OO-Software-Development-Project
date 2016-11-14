package serviceCenter.reviewerServices;

import users.User;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Patric on 2016-11-14.
 */
public class ReviewQueue<T> {

    private Queue<ReviewObject<T>> reviewerQueue = new LinkedBlockingQueue<>();

    public void submit(T object, User user){
        this.reviewerQueue.add(new ReviewObject<>(object, user));
    }

    public ReviewObject<T> retrieve(){
        return this.reviewerQueue.poll();
    }

}
