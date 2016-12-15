package usersService;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and manages a record of arena.users.
 */
public class UsersService implements IUsersService {

    private MapStorage<Integer, User> userStorage = new MapStorage<>();

    @Override
    public MapStorage<Integer, User> getUserStorage(){
        return this.userStorage;
    }

    @Override
    public User createUserFromStringPairs(Map<String, String> pairs){
        String name = pairs.containsKey("name") ? pairs.get("name") : "unnamed";
        String password = pairs.containsKey("password") ? pairs.get("password") : "password";

        User user = new User(name, password);
        System.out.println(user.toString());
        return user;
    }

    @Override
    public User[] getAllUsersAsArray(){
        return getUserStorage().getAll().stream().toArray(User[]::new);
    }

    @Override
    public User getUser(int id){
        return this.userStorage.get(id);
    }

    @Override
    public User getUser(int id, String password){
        if(this.userStorage.get(id).isAuthorized(password))
            return this.userStorage.get(id);
        else
            return null;
            /*
            return createUserFromStringPairs(
                    new HashMap<String, String>(){{
                        put("name", "unauthorized");
                        put("password", "unauthorized");
                    }}
            );*/
    }

    @Override
    public User getUser(String username, String password){
        User user = getUserByName(username);
        if(user.isAuthorized(password))
            return this.userStorage.get(user.getId());
        else
            return null;
    }

    @Override
    public User getUserByName(String username){
        return userStorage.getAll().stream()
                .filter(user -> user.getName().equals(username))
                .findFirst()
                .get();
    }
}
