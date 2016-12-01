package adService;

import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

/**
 * Created by August on 2016-12-01.
 */
public class AdServiceAPI extends ReST {

    private final AdService adService;

    public AdServiceAPI(AdService service){
        this.adService = service;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {

    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {

    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {

    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {

    }
}
