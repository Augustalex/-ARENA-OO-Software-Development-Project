package arena.users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import rest.Delivery;
import rest.PropertyDelivery;

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

    private static Map<String, String> loginMock = new HashMap<>();

    static{
        User.loginMock.put("august", "fuckoff1");
        User.loginMock.put("johan", "twatcunt2");
        User.loginMock.put("carlos", "whatthehell3");
    }

    public final int id;
    private String name;

    /**
     * Will retrieve a User from the UsersService. If the password don't match
     * the password attached to the entered username, the service will return a
     * "Forbidden" HTTP response and the method will not return a valid object (possibly null).
     *
     * In order to not freeze the application and the UI, the method returns a {@link rest.Delivery}
     * object! A callback may be attached to delivery and will be executed in future when the delivery arrives.
     *          Confused? Google "Asynchronous programming" to learn more. The object also relates closely to
     *          other programming concepts such as "Promises" in javascript or "Future" in Java EE.
     * @param username
     * @param password
     * @return
     */
    public static Delivery<IUser> getUser(String username, String password){
        //TODO collect User details from the UsersService instead of creating a mock
        Delivery<IUser> delivery = new PropertyDelivery<>();

        new Thread(() -> {
            if(User.loginMock.containsKey(username.toLowerCase())
                && User.loginMock.get(username.toLowerCase()).equals(password))
                    delivery.deliver(
                            new Gson()
                                    .fromJson(
                                            "{name:"+username.toLowerCase()+",id:-1}",
                                            User.class
                                    ));
            else
                delivery.cancel();
        }).start();

        return delivery;
    }

    public static IUser getGuestUser(){
        return new User(-1, "Guest");
    }

    public static IUser createMockUser(String name){
        return new User(-1, name);
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
