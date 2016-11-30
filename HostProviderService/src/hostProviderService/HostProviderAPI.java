package hostProviderService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Allows for a centralized collection of hosts to join
 * as host and later host services.
 */
public class HostProviderAPI extends ReST {

    private Gson gson = new Gson();

    private HostProvider hostProvider;

    public HostProviderAPI(HostProvider hostProvider){
        this.hostProvider = hostProvider;
    }

    /**
     * Returns a Host object if any host is available.
     *
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws IOException {
        //TODO Asyn! Implement with promises/future (Delivery perhaps)
        try{
            HostService host = hostProvider.getNextAvailableHost();
            sendStringContentResponse(HttpURLConnection.HTTP_OK, gson.toJson(host), httpExchange);
            System.out.println("Sent new host information [hostProvider]");
        } catch (NoAvailableHosts noAvailableHosts) {
            sendEmptyResponse(HttpURLConnection.HTTP_NO_CONTENT, httpExchange);
            System.out.println("Could not find any new host.");
        }
    }

    /**
     * Post as available host.
     *
     * Content in post should be a Host object,
     * that is the connection details for the host.
     *
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        String body = getStringBodyFromHttpExchange(httpExchange);
        hostProvider.addHost(gson.fromJson(body, HostService.class));

        sendEmptyResponse(HttpURLConnection.HTTP_OK, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        //TODO implement shutdown host
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }
}
