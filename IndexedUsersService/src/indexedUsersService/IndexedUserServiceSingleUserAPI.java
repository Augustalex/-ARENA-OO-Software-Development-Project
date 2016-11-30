package indexedUsersService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import org.apache.http.client.fluent.Request;
import rest.Delivery;
import rest.PropertyDelivery;
import rest.ReST;
import serviceIndexer.ServiceIndexer;
import usersService.User;

import javax.management.ServiceNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

/**
 * Created by August on 2016-11-29.
 */
public class IndexedUserServiceSingleUserAPI extends ReST {

    private Gson gson = new Gson();
    private final ServiceIndexer indexer;

    public IndexedUserServiceSingleUserAPI(ServiceIndexer indexer){
        this.indexer = indexer;
    }

    /**
     * Get single user.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        Delivery<User> result = new PropertyDelivery<>();

        new Thread(() -> {
            User user;
            try {
                int index = getIndexFromHttpURI(httpExchange);
                Host serviceInstanceConnectionDetails = indexer.getServiceConnectionDetails(index);
                user = gson.fromJson(Request.Get(serviceInstanceConnectionDetails.getURL())
                        .execute()
                        .returnContent().asString(), User.class);
            } catch (Exception e) {
                user = new User("Unknown", -1);
            }
            result.deliver(user);
        }).start();

        result.onDelivery(user -> {
            try {
                sendStringContentResponse(HttpURLConnection.HTTP_OK, gson.toJson(user), httpExchange);
            } catch (IOException e) {
                System.out.println("Unable to send response.");
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        //TODO implement delete user
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        //TODO implement update user
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    private int getIndexFromHttpURI(HttpExchange httpExchange){
        return Arrays.stream(httpExchange.getHttpContext().getPath()
                .split("/"))
                .reduce((first, last) -> last) //return last element in array
                .map(Integer::parseInt).get();
    }
}
