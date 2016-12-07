package arena.users;

/**
 * A IUser profile that allows privileges and contains user information and stats.
 */
public interface IUser {

    /**
     * Method for notifying the user.
     * @param message
     */
    void notify(String message);

    /**
     * Method for getting the name of the user.
     * @return String name
     */
    String getName();

    int getId();

}
