package serviceIndexer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import hostProviderService.HostService;
import rest.Delivery;
import services.IService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceIndexerAPI extends ServiceIndexerAPIBase {

    private final Gson gson = new Gson();

    public ServiceIndexerAPI(IServiceIndexer serviceIndexer) {
        super(serviceIndexer);
    }

    /**
     * Sends a list of all host information objects
     * representing all active service instances connection
     * details.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        List<HostService> services = getServiceIndexer().getAllServicesConnectionDetails();

        HostService[] hostInformation = services.stream().toArray(HostService[]::new);

        String response = gson.toJson(hostInformation);

        sendStringContentResponse(HttpURLConnection.HTTP_OK, response, httpExchange);
    }

    /**
     * Given an integer provided in the post, the ServiceIndexer
     * will instantiate that number of new services.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        String body = getStringBodyFromHttpExchange(httpExchange);
        System.out.println("ServiceIndexer Post body: " + body);
        int numberOfInstances = Integer.parseInt(body);

        getServiceIndexer().scaleUp(numberOfInstances, wasSuccessful -> {
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
