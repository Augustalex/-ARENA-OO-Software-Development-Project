package serviceDirectory;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import javafx.util.Pair;
import rest.ReST;

import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-15.
 */
public class ServiceDirectoryAPI extends ReST {

    private ServiceDirectory directory;

    public ServiceDirectoryAPI(ServiceDirectory directory){
        this.directory = directory;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        //TODO return all elements
       /* sendStringContentResponse(
                HttpURLConnection.HTTP_OK,
                directory.getAll().stream();
                httpExchange
        );*/
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        String body = getStringBodyFromHttpExchange(httpExchange);

        Pair<String, Host> info = new Gson().fromJson(body, Pair.class);

        String id = directory.publish(
            info.getKey(),
            info.getValue()
        );

        sendStringContentResponse(HttpURLConnection.HTTP_OK, id, httpExchange);
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
