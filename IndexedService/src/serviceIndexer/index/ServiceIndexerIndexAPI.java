package serviceIndexer.index;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import serviceIndexer.IServiceIndexer;
import serviceIndexer.ServiceIndexerAPIBase;
import services.IService;

import javax.management.ServiceNotFoundException;
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
        try{

            int index = getIndexFromHttpURI(httpExchange);

            Host service = getServiceIndexer().getServiceConnectionDetails(index);
            String response = gson.toJson(service);

            sendStringContentResponse(HttpURLConnection.HTTP_OK, response, httpExchange);
        }
        catch(ServiceNotFoundException ex){
            sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
        }
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
