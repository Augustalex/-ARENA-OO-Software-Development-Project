import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Created by August on 2016-11-26.
 */
public class UsersAPI extends ReST {

    private Gson gson = new Gson();

    private UsersService usersService;

    public UsersAPI(UsersService usersService){
        this.usersService = usersService;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        //String response = usersToJson(this.usersService.getAll());
        String response = "";

        System.out.println("GET REQUEST!");
        for(User user : usersService.getAllUsersAsArray())
            response += "Name: " + user.getName() + "\tAge: " + user.getAge() + "\tID: " + user.getId() + "\r\n";

        sendStringContentResponse(HttpURLConnection.HTTP_OK, response, httpExchange);
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

        sendEmtpyResponse(HttpURLConnection.HTTP_OK, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {

    }

    private String getStringBodyFromHttpExchange(HttpExchange httpExchange) throws IOException {
        int contentLength = Integer.parseInt(httpExchange.getRequestHeaders().getFirst("Content-length"));

        InputStreamReader reader = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        char[] buffer = new char[contentLength];
        for(int i = 0; i < contentLength; i++)
            buffer[i] = (char) reader.read();

        System.out.println("This is the json content: " + String.valueOf(buffer));
        return String.valueOf(buffer);
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

    private void sendStringContentResponse(int statusCode, String content, HttpExchange http) throws IOException {
        http.sendResponseHeaders(statusCode, content.length());
        OutputStream stream = http.getResponseBody();
        stream.write(content.getBytes());
        stream.flush();
        stream.close();
    }

    private void sendEmtpyResponse(int statusCode, HttpExchange http) throws IOException {
        http.sendResponseHeaders(statusCode, 0);
    }
}
