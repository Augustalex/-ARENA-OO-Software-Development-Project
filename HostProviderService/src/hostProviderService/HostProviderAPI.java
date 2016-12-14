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
        //TODO Async! Implement with promises/future (Delivery perhaps)
        try{
            HostService host = hostProvider.getNextAvailableHost();
            sendStringContentResponse(HttpURLConnection.HTTP_OK, gson.toJson(host), httpExchange);
            System.out.println("Sent new host information [hostProvider]");
        } catch (NoAvailableHosts noAvailableHosts) {
            try {
                sendStringContentResponse(HttpURLConnection.HTTP_NO_CONTENT, "", httpExchange);
            }
            catch(Exception ex){
                System.out.println("Other error occured: " + ex.getMessage() + "\nCause: " + ex.getCause());
            }
            System.out.println("Could not find any new host.");
        }
        catch(Exception ex){
            System.out.println("Other error occured: " + ex.getMessage() + "\nCause: " + ex.getCause());
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
        System.out.println("Post for new host!");
        String body = getStringBodyFromHttpExchange(httpExchange);
        String id = hostProvider.addHost(gson.fromJson(body, HostService.class));

        sendStringContentResponse(HttpURLConnection.HTTP_OK, id, httpExchange);
        //sendEmptyResponse(HttpURLConnection.HTTP_OK, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }
}
