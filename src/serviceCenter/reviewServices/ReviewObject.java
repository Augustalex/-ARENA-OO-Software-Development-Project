package serviceCenter.reviewServices;

import users.User;

/**
 * Contains an object that is up for a retrieve, as well as the User
 * who submitted the object.
 */
public class ReviewObject<T> {

    private T reviewObject;
    private User sender;

    public ReviewObject(T reviewObject, User user){
        this.reviewObject = reviewObject;
        this.sender = user;
    }

    public T getReviewObject(){
        return this.reviewObject;
    }

    public User getSender(){
        return this.sender;
    }

    public void disapprove(){
        this.sender.notify("Your submission of: " + reviewObject + " was not approved.");
    }
}
