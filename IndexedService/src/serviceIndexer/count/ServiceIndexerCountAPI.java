package serviceIndexer.count;

import com.sun.net.httpserver.HttpExchange;
import rest.ReST;
import serviceIndexer.IServiceIndexer;
import serviceIndexer.ServiceIndexer;
import serviceIndexer.ServiceIndexerAPIBase;
import services.IService;

import java.net.HttpURLConnection;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceIndexerCountAPI extends ServiceIndexerAPIBase {

    public ServiceIndexerCountAPI(IServiceIndexer serviceIndexer) {
        super(serviceIndexer);
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        String response = String.valueOf(getServiceIndexer().serviceCount());
        sendStringContentResponse(HttpURLConnection.HTTP_OK, response, httpExchange);
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
