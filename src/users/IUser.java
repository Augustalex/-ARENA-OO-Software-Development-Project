package users;

/**
 * A IUser profile that allows privileges and contains user information and stats.
 */
public interface IUser {

    void notify(String message);

    String getName();

}
