package indexedSubStreamer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import org.apache.http.client.fluent.Request;
import rest.Delivery;
import rest.PropertyDelivery;
import rest.ReST;
import serviceIndexer.ServiceIndexer;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-15.
 */
public class IndexedSubStreamerAPI extends ReST {

    private static final int maxSubStreamCapacity = 3;
    private final SubStreamIndexer serviceIndexer;

    private boolean hasLiveStreamConnectionDetails = false;

    public IndexedSubStreamerAPI(Host hostProviderDetails){
        this.serviceIndexer = new SubStreamIndexer(maxSubStreamCapacity, hostProviderDetails, "SubStream");
    }

    public IndexedSubStreamerAPI setLiveStreamConnectionDetails(Host liveStreamConnectionDetails){
        this.serviceIndexer.setLiveStreamDetails(liveStreamConnectionDetails);
        this.hasLiveStreamConnectionDetails = true;
        return this;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        new Thread(() -> {
            System.out.println("GET REQUEST TO INDEXED LIVE STREAM!");
            System.out.println("Has live stream connection details? " + hasLiveStreamConnectionDetails);
            if(hasLiveStreamConnectionDetails) {
                int id = serviceIndexer.getNextObjectId();
                serviceIndexer.getServiceConnectionDetails(id)
                        .onCancel(() -> {
                            System.out.println("Could not get a new indexed host.");
                            try {
                                sendStringContentResponse(HttpURLConnection.HTTP_INTERNAL_ERROR, "", httpExchange);
                            } catch (IOException e) {
                                System.out.println("Could not send response.");
                                e.printStackTrace();
                            }
                        })
                        .onDelivery(hostService -> {
                            try {
                                /*String streamConnectionDetailsJson =
                                        Request.Get(hostService.getURL())
                                                .execute()
                                                .returnContent().asString();
*/
                                System.out.println("Got details: " + hostService.getURL() + ", sending them now!");

                                sendStringContentResponse(HttpURLConnection.HTTP_OK, new Gson().toJson(hostService), httpExchange);
                                System.out.println("Sent connection details to service.");
                            } catch (IOException e) {
                                System.out.println("Could not send response.");
                                e.printStackTrace();
                            }
                        });
            }
            else
                try {
                    sendStringContentResponse(HttpURLConnection.HTTP_SEE_OTHER, "", httpExchange);
                } catch (IOException e) {
                    System.out.println("Could not send response.");
                    e.printStackTrace();
                }
        }).start();
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        String body = getStringBodyFromHttpExchange(httpExchange);

        Host liveStreamConnectionDetails = new Gson().fromJson(body, Host.class);

        setLiveStreamConnectionDetails(liveStreamConnectionDetails);

        sendStringContentResponse(HttpURLConnection.HTTP_OK, "", httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }
}
