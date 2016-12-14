package hostProviderService;

import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-14.
 */
public class HostProviderSingleAPI extends ReST {

    private HostProvider hostProvider;

    public HostProviderSingleAPI(HostProvider hostProvider){
        this.hostProvider = hostProvider;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        String id = getIdFromHttpURI(httpExchange);
        hostProvider.removeHost(id);

        sendStringContentResponse(HttpURLConnection.HTTP_OK, "", httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }
}
