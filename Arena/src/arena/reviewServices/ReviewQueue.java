package arena.reviewServices;

import arena.users.IUser;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A que that contains something that needs to be reviewed
 *
 * Can be used as the review que for the operator.
 */
public class ReviewQueue<T> {

    private Queue<ReviewObject<T>> reviewerQueue = new LinkedBlockingQueue<>();

    public void submit(T object, IUser IUser){
        this.reviewerQueue.add(new ReviewObject<>(object, IUser));
    }

    public ReviewObject<T> retrieve(){
        return this.reviewerQueue.poll();
    }

}
