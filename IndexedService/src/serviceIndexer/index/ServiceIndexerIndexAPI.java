package serviceIndexer.index;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import indexedService.IIndexedService;
import rest.ReST;
import serviceIndexer.IServiceIndexer;
import serviceIndexer.count.ServiceIndexerCountAPI;

import javax.management.ServiceNotFoundException;
import java.net.HttpURLConnection;
import java.util.Arrays;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceIndexerIndexAPI extends ReST {

    private final Gson gson = new Gson();
    private final IServiceIndexer serviceIndexer;

    public ServiceIndexerIndexAPI(IServiceIndexer serviceIndexer){
        this.serviceIndexer = serviceIndexer;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        try{
            String[] uri = httpExchange
                    .getHttpContext()
                    .getPath()
                    .split("/");

            int index = Integer.parseInt(uri[uri.length-1]);

            IIndexedService service = serviceIndexer.getService(index);
            String response = gson.toJson(service);
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
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }
}
