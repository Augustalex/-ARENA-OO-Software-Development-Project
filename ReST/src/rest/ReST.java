package rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.HttpEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Methods for completely handling every rest.ReST API Use Case.
 */
public abstract class ReST implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        System.out.println("HTTP INCOMING");
        System.out.println(httpExchange.getRequestMethod());
    /*    if(methodName.equals("POST"))
            System.out.println(httpExchange.getRequestMethod() + "\n" + getStringBodyFromHttpExchange(httpExchange));
        else
            System.out.println(httpExchange.getRequestMethod());*/
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
        String length = httpExchange.getRequestHeaders().getFirst("Content-length");
        System.out.println("Length: " + length);
        int contentLength = Integer.parseInt(httpExchange.getRequestHeaders().getFirst("Content-length"));

        InputStreamReader reader = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        char[] buffer = new char[contentLength];
        for(int i = 0; i < contentLength; i++)
            buffer[i] = (char) reader.read();

        return String.valueOf(buffer);
    }

    protected byte[] getByteArrayBodyFromHttpExchange(HttpExchange httpExchange) throws IOException {
        int contentLength = Integer.parseInt(httpExchange.getRequestHeaders().getFirst("Content-length"));

        byte[] byteArray = new byte[contentLength*4];


        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
            for (int i = 0; i < contentLength; i++) {
                int in = inputStream.read();
                System.out.println("int in: " + in + ", char in: " + (char)in + ", String in: " + String.valueOf(in) + ", String char in: " + String.valueOf((char)in));

                byte[] byteBuffer = ByteBuffer.allocate(4).putInt(in).array();
                for(byte b : byteBuffer)
                    byteArray[i++] = b;

                System.out.println("in: " + new String(byteBuffer, Charset.forName("UTF-8")));
            }
        }
        catch(EOFException eof){
            System.out.println("eof.");
            System.out.println("Data read: " + new String(byteArray));
        }

        return byteArray;
    }

    protected <T> T getObjectFromJsonInHttpExchange(HttpExchange httpExchange, Class<T> objectType){
        int contentLength = Integer.parseInt(httpExchange.getRequestHeaders().getFirst("Content-length"));

        Reader reader = new InputStreamReader(httpExchange.getRequestBody());
        T result = new Gson().fromJson(reader, objectType);
        System.out.println("object: " + result.toString());
        System.out.println("Type: " + objectType.getName());

        return result;
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

    public String getIdFromHttpURI(HttpExchange httpExchange){
        String path = httpExchange.getRequestURI().getRawPath();
        String[] splitPath = path.split("/");

        return splitPath[splitPath.length-1];
    }

    public Map<String, String> getStringPairsFromJson(String json){
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return new Gson().fromJson(json, type);
    }
}
