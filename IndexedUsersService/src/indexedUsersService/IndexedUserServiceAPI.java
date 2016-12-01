package indexedUsersService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.HostService;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import rest.Delivery;
import rest.PropertyDelivery;
import rest.ReST;
import serviceIndexer.ServiceIndexer;
import usersService.User;

import javax.management.ServiceNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by August on 2016-11-29.
 */
public class IndexedUserServiceAPI extends ReST {

    private final Object key = new Object();

    private Gson gson = new Gson();
    private ServiceIndexer indexer;

    private int nextAvailableIndex = 0;

    public IndexedUserServiceAPI(ServiceIndexer serviceIndexer){
        this.indexer = serviceIndexer;
    }

    /**
     * Get all arena.users as an array of User class.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        System.out.println("IndexedUsersService GET Request.");
        Delivery<User[]> result = new PropertyDelivery<>();

        result.onDelivery(users -> {
            try {
                sendStringContentResponse(HttpURLConnection.HTTP_OK, gson.toJson(users), httpExchange);
            } catch (IOException e) {
                System.out.println("Unable to send response.");
                System.out.println(e.getMessage());
            }
        });

        new Thread(() -> {
            result.deliver(indexer.getAllServicesConnectionDetails()
                    .parallelStream()
                    .flatMap(hostInfo -> {
                        User[] users;
                        try {
                            String responseContent = Request.Get(hostInfo.getURL()).execute().returnContent().asString();
                            System.out.println("response content:");
                            System.out.println(responseContent);
                            users = gson.fromJson(responseContent, User[].class);
                            System.out.println("Got some arena.users: " + users);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Were unable to find any arena.users at " + hostInfo.getURL());
                            users = new User[]{};
                        }

                        return Arrays.stream(users);
                    })
                    .toArray(User[]::new));
        }).start();
    }

    /**
     * Post a single user of type User class.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        indexer
                .getServiceConnectionDetails(getNextAvailableIndex())
                .onDelivery(connectionDetails -> {
                    try {
                        if(connectionDetails == null)
                            sendEmptyResponse(HttpURLConnection.HTTP_INTERNAL_ERROR, httpExchange);
                        else {
                            CloseableHttpResponse response = postNewUser(httpExchange, connectionDetails.getURL());
                            httpExchange.sendResponseHeaders(response.getStatusLine().getStatusCode(), 0);
                        }
                    } catch (IOException e) {
                        System.out.println("Could not send response headers.");
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Delete all arena.users.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        //TODO implement delete all arena.users.
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    /**
     * Update all arena.users?
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    private CloseableHttpResponse postNewUser(HttpExchange httpExchange, String serviceInstanceConnectionURL) {
        String body = getBody(httpExchange);
        CloseableHttpResponse response = null;

        try {
            response = post(body, serviceInstanceConnectionURL);
        } catch (IOException e) {
            System.out.println("Could not send request.");
            e.printStackTrace();
        }

        return response;
    }

    private String getBody(HttpExchange httpExchange){
        String result = "";

        try{
            result = getStringBodyFromHttpExchange(httpExchange);
        } catch (IOException e) {
            System.out.println("Could not retrieve request body.");
            e.printStackTrace();
        }

        return result;
    }

    private CloseableHttpResponse post(String body, String url) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new ByteArrayEntity(body.getBytes(StandardCharsets.UTF_8)));
        return HttpClients.createDefault().execute(post);
    }

    private int getNextAvailableIndex(){
        synchronized (key) {
            //TODO implement queues for what service indexes have become available, and pick from that queue before grabbing the "next available" index.
            System.out.println("Got next available index: " + this.nextAvailableIndex);
            return this.nextAvailableIndex++;
        }
    }
}
