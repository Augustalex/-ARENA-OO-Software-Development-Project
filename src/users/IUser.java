package users;

/**
 * A IUser profile that allows privileges and contains user information and stats.
 */
public interface IUser {

    static IUser newMockUser(){
        return new IUser() {
            @Override
            public void notify(String message) {
                System.out.println("NOTIFY! " + message);
            }

            @Override
            public String getName() {
                return "August";
            }

        };
    }

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


}
