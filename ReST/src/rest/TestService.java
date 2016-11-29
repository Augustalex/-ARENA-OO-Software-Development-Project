package rest;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

/**
 * Created by August on 2016-11-26.
 */
public class TestService extends ReST {

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        onPost(httpExchange);
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        String response = "Hello Client!";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();    }

    @Override
    public void onDelete(HttpExchange httpExchange) {

    }

    @Override
    public void onPut(HttpExchange httpExchange) {

    }
}
