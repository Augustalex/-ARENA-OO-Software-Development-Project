package rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Methods for completely handling every rest.ReST API Use Case.
 */
public abstract class ReST implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExchange){
        try {
            switch (httpExchange.getRequestMethod()) {
                case "GET":
                    this.onGet(httpExchange);
                    break;
                case "POST":
                    this.onPost(httpExchange);
                    break;
                case "PUT":
                    this.onPut(httpExchange);
                    break;
                case "DELETE":
                    this.onDelete(httpExchange);
                    break;
                default:
                    this.onGet(httpExchange);
            }
        }
        catch(Exception e){
            System.out.println("Error in http handler for context: " + httpExchange.getHttpContext().getPath());
            e.printStackTrace();
        }
    }

    public abstract void onGet(HttpExchange httpExchange) throws Exception;

    public abstract void onPost(HttpExchange httpExchange) throws Exception;

    public abstract void onDelete(HttpExchange httpExchange) throws Exception;

    public abstract void onPut(HttpExchange httpExchange) throws Exception;

    protected String getStringBodyFromHttpExchange(HttpExchange httpExchange) throws IOException {
        int contentLength = Integer.parseInt(httpExchange.getRequestHeaders().getFirst("Content-length"));

        InputStreamReader reader = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        char[] buffer = new char[contentLength];
        for(int i = 0; i < contentLength; i++)
            buffer[i] = (char) reader.read();

        return String.valueOf(buffer);
    }

    protected void sendStringContentResponse(int statusCode, String content, HttpExchange http) throws IOException {
        http.sendResponseHeaders(statusCode, content.length());
        OutputStream stream = http.getResponseBody();
        stream.write(content.getBytes());
        stream.flush();
        stream.close();
    }

    protected void sendEmptyResponse(int statusCode, HttpExchange http) throws IOException {
        http.sendResponseHeaders(statusCode, 0);
    }
}
