
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores and manages a record of users.
 */
public class UsersService {

    private MapStorage<Integer, User> userStorage = new MapStorage<>();

    public MapStorage<Integer, User> getUserStorage(){
        return this.userStorage;
    }

    public User createUserFromStringPairs(Map<String, String> pairs){
        String name = pairs.containsKey("name") ? pairs.get("name") : "unnamed";
        int age = Integer.parseInt(pairs.containsKey("age") ? pairs.get("age") : "0");

        System.out.println("name: " + name + ", age: " + age);
        User user = new User(name, age);
        System.out.println(user.toString());
        return user;
    }

    public User[] getAllUsersAsArray(){
        return getUserStorage().getAll().stream().toArray(User[]::new);
    }
}
