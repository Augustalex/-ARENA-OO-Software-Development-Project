package indexedUsersService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.apache.http.client.fluent.Request;
import rest.ReST;
import serviceIndexer.ServiceIndexer;

import java.io.IOException;
import java.net.HttpURLConnection;

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
        indexer
                .getServiceConnectionDetails(getIndexFromHttpURI(httpExchange))
                .onDelivery(hostService -> {
                    try{
                        if(hostService == null)
                            sendEmptyResponse(HttpURLConnection.HTTP_INTERNAL_ERROR, httpExchange);
                        else
                            sendStringContentResponse(
                                    HttpURLConnection.HTTP_OK,
                                    getUserDataBytes(hostService.getURL() + httpExchange.getRequestURI().getRawPath()),
                                    httpExchange
                            );
                    } catch (IOException e) {
                        System.out.println("Could no send response content.");
                        e.printStackTrace();
                    }
                });
    }

    private String getUserDataBytes(String url) {
        try {
            return Request.Get(url)
                    .execute()
                    .returnContent().asString();
        } catch (IOException e) {
            System.out.println("Could not send Request to " + url);
            e.printStackTrace();

            //TODO might need better error handling.. Json problems could become of this.
            return "";
        }
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
        /*return Arrays.stream(httpExchange.getHttpContext().getPath()
                .split("/"))
                .reduce((first, last) -> last) //return last element in array
                .map(Integer::parseInt).get();*/
        String path = httpExchange.getRequestURI().getRawPath();
        String[] splitPath = path.split("/");

        return Integer.parseInt(splitPath[splitPath.length-1]);
    }
}
