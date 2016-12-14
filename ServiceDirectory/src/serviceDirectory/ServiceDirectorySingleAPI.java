package serviceDirectory;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import rest.ReST;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-15.
 */
public class ServiceDirectorySingleAPI extends ReST {

    private ServiceDirectory directory;

    public ServiceDirectorySingleAPI(ServiceDirectory directory){
        this.directory = directory;
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        String uri = httpExchange.getRequestURI().getRawPath();

        if(identifiesUniqueService(uri)){
            String[] ids = getIdParametersFromURI(uri);
            directory.get(ids[0], ids[1])
                    .onCancel(() -> {
                        try {
                            sendStringContentResponse(HttpURLConnection.HTTP_BAD_REQUEST, "", httpExchange);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .onDelivery(host -> {
                        try {
                            sendStringContentResponse(
                                    HttpURLConnection.HTTP_OK,
                                    new Gson().toJson(host),
                                    httpExchange
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        else if(identifiesServiceType(uri)){
            directory.grab(getServiceTypeFromURI(uri))
                    .onCancel(() -> {
                        try {
                            sendStringContentResponse(HttpURLConnection.HTTP_BAD_REQUEST, "", httpExchange);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .onDelivery(host -> {
                        try {
                            sendStringContentResponse(
                                    HttpURLConnection.HTTP_OK,
                                    new Gson().toJson(host),
                                    httpExchange
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });        }
        else
            sendStringContentResponse(HttpURLConnection.HTTP_BAD_REQUEST, "", httpExchange);
    }

    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }

    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        String uri = httpExchange.getRequestURI().getRawPath();

        if(identifiesUniqueService(uri)){
            String[] ids = getIdParametersFromURI(uri);
            directory.remove(ids[0], ids[1]);
        }
        else
            sendStringContentResponse(HttpURLConnection.HTTP_BAD_REQUEST, "", httpExchange);
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendStringContentResponse(HttpURLConnection.HTTP_BAD_METHOD, "", httpExchange);
    }

    /**
     * Vaguely tells if a URI uniquely identifies a service.
     * (Just looks at the number of dash separated strings in the uri).
     * @param path
     * @return
     */
    private boolean identifiesUniqueService(String path){
        if(path.split("/").length < 2)
            return false;
        else
            return true;
    }

    /**
     * Vaguely tells if a URI uniquely identifies a Service Type.
     * (Just looks at the number of dash separated strings in the uri).
     * @param path
     * @return
     */
    private boolean identifiesServiceType(String path){
        if(path.split("/").length < 1 || path.split("/").length > 2)
            return false;
        else
            return true;
    }

    private String getServiceTypeFromURI(String path){
        String[] splitPath = path.split("/");

        if(splitPath.length < 2)
            return splitPath[splitPath.length-1];
        else
            return splitPath[splitPath.length-2];
    }

    private String[] getIdParametersFromURI(String path) throws IOException {
        String[] splitPath = path.split("/");

        if(splitPath.length < 2)
            throw new RuntimeException("URI not correct. Is not uniquely identifying.");

        return new String[]{
                splitPath[splitPath.length-2],
                splitPath[splitPath.length-1]
        };
    }
}
