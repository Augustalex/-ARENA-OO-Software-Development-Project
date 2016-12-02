package boardGameLibrary.playerProfileStore.exceptions;

/**
 * Created by August on 2016-10-25.
 */
public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(){
        super("Profile not found. No profile with given selector could be found.");
    }
}
