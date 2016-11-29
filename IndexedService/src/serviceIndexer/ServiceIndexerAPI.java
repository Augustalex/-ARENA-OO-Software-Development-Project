package serviceIndexer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import indexedService.IIndexedService;
import javafx.beans.property.BooleanProperty;
import rest.Delivery;
import rest.ReST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceIndexerAPI extends ReST {

    private final Gson gson = new Gson();

    private final IServiceIndexer serviceIndexer;

    public ServiceIndexerAPI(IServiceIndexer service){
        this.serviceIndexer = service;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        IIndexedService[] services = serviceIndexer.getAllServices();

        String response = gson.toJson(services);

        sendStringContentResponse(HttpURLConnection.HTTP_OK, response, httpExchange);
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        String body = getStringBodyFromHttpExchange(httpExchange);

        int numberOfInstances = Integer.parseInt(body);
        Delivery<Boolean> status = serviceIndexer.scaleUp(numberOfInstances);

        status.onDelivery(wasSuccessful -> {
            try {
                if (wasSuccessful)
                    sendEmptyResponse(HttpURLConnection.HTTP_OK, httpExchange);
                else
                    sendEmptyResponse(HttpURLConnection.HTTP_INTERNAL_ERROR, httpExchange);
            }
            catch(IOException ex){
                System.out.println("Failure to send response.");
            }
        });

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
