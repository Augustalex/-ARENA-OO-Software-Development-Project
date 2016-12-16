package subStream;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import rest.ReST;

import java.net.HttpURLConnection;

/**
 * Receives a Host connection details object that the SubStream uses
 * to relay its output to multiple other clients.
 */
public class SubStreamAPI extends ReST {

    private final SubStream subStream;

    public SubStreamAPI(SubStream subStream){
        this.subStream = subStream;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        System.out.println("SUBSTREAM GET");
        if(subStream.isFull())
            sendStringContentResponse(HttpURLConnection.HTTP_SEE_OTHER, "", httpExchange);
        else {
            String responseContent = new Gson().toJson(subStream.getStreamConnectionDetails());
            sendStringContentResponse(HttpURLConnection.HTTP_OK, responseContent, httpExchange);
        }
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        System.out.println("SubStream POST REQUEST");
        if(subStream.isRunning())
            sendStringContentResponse(HttpURLConnection.HTTP_SEE_OTHER, "", httpExchange);
        else{
            Host host = new Gson().fromJson(getStringBodyFromHttpExchange(httpExchange), Host.class);
            subStream.receiveStream(host);
            sendStringContentResponse(HttpURLConnection.HTTP_OK, "", httpExchange);
        }
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        subStream.stopStream();
        sendStringContentResponse(HttpURLConnection.HTTP_OK, "", httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }
}
