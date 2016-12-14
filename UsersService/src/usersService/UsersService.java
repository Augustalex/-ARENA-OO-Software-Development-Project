package usersService;

import java.util.Map;

/**
 * Stores and manages a record of arena.users.
 */
public class UsersService {

    private MapStorage<Integer, User> userStorage = new MapStorage<>();

    public MapStorage<Integer, User> getUserStorage(){
        return this.userStorage;
    }

    public User createUserFromStringPairs(Map<String, String> pairs){
        String name = pairs.containsKey("name") ? pairs.get("name") : "unnamed";
        String password = pairs.containsKey("password") ? pairs.get("password") : "password";

        User user = new User(name, password);
        System.out.println(user.toString());
        return user;
    }

    public User[] getAllUsersAsArray(){
        return getUserStorage().getAll().stream().toArray(User[]::new);
    }

    public User getUser(int id){
        return this.userStorage.get(id);
    }
}
