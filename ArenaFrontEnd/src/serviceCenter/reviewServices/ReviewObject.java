package serviceCenter.reviewServices;

import users.IUser;

/**
 * Contains an object that is up for a retrieve, as well as the IUser
 * who submitted the object.
 */
public class ReviewObject<T> {

    private T reviewObject;
    private IUser sender;

    public ReviewObject(T reviewObject, IUser IUser){
        this.reviewObject = reviewObject;
        this.sender = IUser;
    }

    public T getReviewObject(){
        return this.reviewObject;
    }

    public IUser getSender(){
        return this.sender;
    }

    public void disapprove(){
        this.sender.notify("Your submission of: " + reviewObject + " was not approved.");
    }

    @Override
    public String toString(){
        return "ReviewObject from " + this.sender.toString() + ": " + this.reviewObject.toString();
    }
}
