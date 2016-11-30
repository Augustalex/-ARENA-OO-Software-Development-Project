package indexedUsersService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.apache.http.Header;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import rest.Delivery;
import rest.PropertyDelivery;
import rest.ReST;
import serviceIndexer.ServiceIndexer;
import usersService.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
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
     * Get all users as an array of User class.
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
                            System.out.println("Got some users: " + users);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Were unable to find any users at " + hostInfo.getURL());
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
        int index = getNextAvailableIndex();

        int serviceCount = indexer.serviceCount();
        int unitCapacity = indexer.getPartitionCapacity();

        if(index >= unitCapacity*serviceCount) {
            indexer.scaleUp(
                    1,
                    wasSuccessful -> {
                        try {
                            if(wasSuccessful){
                                postNewUser(httpExchange, index);
                            }
                            else
                            sendEmptyResponse(HttpURLConnection.HTTP_INTERNAL_ERROR, httpExchange);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        else{
            postNewUser(httpExchange, index);
        }
    }

    private void postNewUser(HttpExchange httpExchange, int index){
        try {
            String serviceInstanceConnectionURL = this.indexer.getServiceConnectionDetails(index).getURL();
            String body = getStringBodyFromHttpExchange(httpExchange);
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(serviceInstanceConnectionURL);
            post.setEntity(new ByteArrayEntity(body.getBytes(StandardCharsets.UTF_8)));
            CloseableHttpResponse response =  httpclient.execute(post);


            httpExchange.sendResponseHeaders(response.getStatusLine().getStatusCode(), response.getEntity().getContentLength());
        } catch (Exception ex) {
            System.out.println("Could not post to service.");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Delete all users.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        //TODO implement delete all users.
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    /**
     * Update all users?
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    private Delivery<String> getResponse(Supplier<String> request){
        Delivery<String> delivery = new PropertyDelivery<>();

        new Thread(() -> delivery.deliver(request.get())).start();

        return delivery;
    }

    private int getNextAvailableIndex(){
        synchronized (key) {
            System.out.println("Got next available index: " + this.nextAvailableIndex);
            return this.nextAvailableIndex++;
        }
    }
}
