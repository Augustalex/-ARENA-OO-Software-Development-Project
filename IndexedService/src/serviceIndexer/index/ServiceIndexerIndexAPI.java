package serviceIndexer.index;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import hostProviderService.HostService;
import rest.Delivery;
import serviceIndexer.IServiceIndexer;
import serviceIndexer.ServiceIndexerAPIBase;
import services.IService;

import javax.management.ServiceNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceIndexerIndexAPI extends ServiceIndexerAPIBase {

    private final Gson gson = new Gson();

    public ServiceIndexerIndexAPI(IServiceIndexer serviceIndexer) {
        super(serviceIndexer);
    }

    /**
     * Sends a Host object of the connection details for the service
     * in charge of index provided in the API URI.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        getServiceIndexer()
                .getServiceConnectionDetails(getIndexFromHttpURI(httpExchange))
                .onDelivery(hostService -> {
                    try{
                        if(hostService == null)
                            sendEmptyResponse(HttpURLConnection.HTTP_INTERNAL_ERROR, httpExchange);
                        else
                            sendStringContentResponse(
                                    HttpURLConnection.HTTP_OK,
                                    gson.toJson(hostService),
                                    httpExchange);
                    } catch (IOException e) {
                        System.out.println("Could not send response content.");
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        //TODO implement service instance shutdown
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        //TODO implement restart service instance
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    private int getIndexFromHttpURI(HttpExchange httpExchange){
        return Arrays.stream(httpExchange.getHttpContext().getPath()
                .split("/"))
                .reduce((first, last) -> last) //return last element in array
                .map(Integer::parseInt).get();
    }
}
