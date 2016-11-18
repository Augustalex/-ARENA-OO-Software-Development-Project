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

    void notify(String message);

    String getName();

}
