package serviceDirectory;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import javafx.util.Pair;
import rest.ReST;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * A overall api for grabbing all active services and
 * posting new services.
 *
 * Deleting services requires a SingleAPI.
 */
public class ServiceDirectoryAPI extends ReST {

    private ServiceDirectory directory;

    public ServiceDirectoryAPI(ServiceDirectory directory){
        this.directory = directory;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        List<Pair<String, Host>> pairs = directory.getAll();

        String[] jsonPairs = pairs.stream()
                .map(pair -> new Gson().toJson(pair))
                .toArray(String[]::new);

        sendStringContentResponse(
                HttpURLConnection.HTTP_OK,
                new Gson().toJson(jsonPairs),
                httpExchange
        );
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
