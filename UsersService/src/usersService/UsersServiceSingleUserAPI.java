package usersService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by August on 2016-11-30.
 */
public class UsersServiceSingleUserAPI extends ReST {

    private Gson gson = new Gson();
    private final UsersService usersService;

    public UsersServiceSingleUserAPI(UsersService usersService){
        this.usersService = usersService;
    }

    @Override
    public void onGet(HttpExchange httpExchange) {
        int index = getIndexFromHttpURI(httpExchange);
        User user = this.usersService.getUser(index);

        try{
            sendStringContentResponse(HttpURLConnection.HTTP_OK, gson.toJson(user), httpExchange);
        } catch (IOException e) {
            System.out.println("Could not send content response.");
            e.printStackTrace();
        }

    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    private int getIndexFromHttpURI(HttpExchange httpExchange){
        /*return Arrays.stream(httpExchange.getHttpContext().getPath()
                .split("/"))
                .reduce((first, last) -> last) //return last element in array
                .map(Integer::parseInt).get();*/
        String path = httpExchange.getRequestURI().getRawPath();
        String[] splitPath = path.split("/");

        return Integer.parseInt(splitPath[splitPath.length-1]);
    }
}
