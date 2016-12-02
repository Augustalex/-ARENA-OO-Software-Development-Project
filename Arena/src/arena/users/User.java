package arena.users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Wraps the information provided by the UserService into a class compatible as an IUser.
 *
 * The id is provided by the service and cannot be manipulated on the client.
 * The main use of the id is for accessing the users details on the user service,
 * such as removing the user or updating its information.
 *
 */
public class User implements IUser {

    public final int id;
    private String name;

    /**
     * Will retrieve a User from the UsersService. If the password don't match
     * the password attached to the entered username, the service will return a
     * "Forbidden" HTTP response and the method will not return a valid object (possibly null).
     * @param username
     * @param password
     * @return
     */
    public static IUser getUser(String username, String password){
        //TODO collect User details from the UsersService
        return createUser("{name:August,id:0}");
    }

    private static IUser createUser(String json){
        Type listType = new TypeToken<HashMap<String,String>>(){}.getType();
        Map<String, String> values = new Gson().fromJson(json, listType);

        return new User(Integer.parseInt(values.get("id")), values.get("name"));
    }

    private User(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println("Message to the user: " + message);
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
}
