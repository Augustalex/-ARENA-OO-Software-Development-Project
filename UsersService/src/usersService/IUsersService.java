package usersService;

import java.util.Map;

/**
 * Created by August on 2016-12-15.
 */
public interface IUsersService {
    MapStorage<Integer, User> getUserStorage();

    User createUserFromStringPairs(Map<String, String> pairs);

    User[] getAllUsersAsArray();

    User getUser(int id);

    User getUser(int id, String password);

    User getUser(String username, String password);

    User getUserByName(String username);
}
