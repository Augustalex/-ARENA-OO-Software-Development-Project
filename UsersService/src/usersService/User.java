package usersService;

/**
 * Created by August on 2016-11-26.
 */
public class User {

    public final int id;
    private String name;

    private String password;

    private static int nextUserId = 0;

    public User(String name, String password){
        this.id = allocateId();
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public boolean isAuthorized(String password){
        return this.password.equals(password);
    }

    private static int allocateId(){
        return nextUserId++;
    }
}
