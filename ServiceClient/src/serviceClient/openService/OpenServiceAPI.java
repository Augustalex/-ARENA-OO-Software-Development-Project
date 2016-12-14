package serviceClient.openService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-14.
 */
public class OpenServiceAPI extends ReST {

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_OK, "Service is yet not available or instantiated.", httpExchange);
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_OK, "Service is yet not available or instantiated.", httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_OK, "Service is yet not available or instantiated.", httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_OK, "Service is yet not available or instantiated.", httpExchange);
    }
}
