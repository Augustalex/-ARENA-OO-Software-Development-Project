package usersService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Created by August on 2016-11-26.
 */
public class UsersServiceAPI extends ReST {

    private Gson gson = new Gson();

    private UsersService usersService;

    public UsersServiceAPI(UsersService usersService){
        this.usersService = usersService;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        String response = gson.toJson(this.usersService.getAllUsersAsArray());
        String output = "";

        System.out.println("GET REQUEST!");
        for(User user : usersService.getAllUsersAsArray()) {
            output += "Name: " + user.getName() + "\tAge: " + user.getAge() + "\tID: " + user.getId() + "\r\n";
        }

        System.out.println("Found arena.users: ");
        System.out.println(output);
        sendStringContentResponse(HttpURLConnection.HTTP_OK, response, httpExchange);
        System.out.println("Sent get request response.");
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {

        System.out.println("POST REQUEST");

        String body = getStringBodyFromHttpExchange(httpExchange);

        User user = usersService.createUserFromStringPairs(
                getStringPairsFromJson(body)
        );

        usersService.getUserStorage().add(user.getId(), user);

        String content = "Created new user: [Name: " + user.getName() + ", Age: " + user.getAge() + "]";
        System.out.println("Response message: \"" + content + "\"");
        sendStringContentResponse(200, content, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        User[] allUsers = this.usersService.getAllUsersAsArray();

        for(User user : allUsers)
            usersService.getUserStorage().remove(user.getId());

        sendEmptyResponse(HttpURLConnection.HTTP_OK, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {

    }

    private Map<String, String> getStringPairsFromJson(String json){
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return gson.fromJson(json, type);
    }

    private String userToJson(User user){
        return gson.toJson(user);
    }

    private String usersToJson(User[] users){
        return gson.toJson(users);
    }
}
