package usersService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.net.HttpURLConnection;

/**
 * Created by August on 2016-11-26.
 */
public class UsersServiceAPI extends ReST {

    private Gson gson = new Gson();

    private IUsersService IUsersService;

    public UsersServiceAPI(IUsersService IUsersService){
        this.IUsersService = IUsersService;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        String response = gson.toJson(this.IUsersService.getAllUsersAsArray());
        String output = "";

        System.out.println("GET REQUEST!");
        for(User user : IUsersService.getAllUsersAsArray()) {
            output += "Name: " + user.getName() + "\tID: " + user.getId() + "\r\n";
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

        User user = IUsersService.createUserFromStringPairs(
                getStringPairsFromJson(body)
        );

        IUsersService.getUserStorage().add(user.getId(), user);

        String content = "Created new user: [Name: " + user.getName() + "]";
        System.out.println("Response message: \"" + content + "\"");
        sendStringContentResponse(200, content, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        User[] allUsers = this.IUsersService.getAllUsersAsArray();

        for(User user : allUsers)
            IUsersService.getUserStorage().remove(user.getId());

        sendEmptyResponse(HttpURLConnection.HTTP_OK, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {

    }

    private String userToJson(User user){
        return gson.toJson(user);
    }

    private String usersToJson(User[] users){
        return gson.toJson(users);
    }
}
