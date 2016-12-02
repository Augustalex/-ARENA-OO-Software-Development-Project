package gameService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import rest.ReST;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by August on 2016-12-02.
 */
public class SingleGameServiceAPI extends GameServiceAPI {

    public SingleGameServiceAPI(GameService service) {g
        super(service);
    }

    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {

    }

    /**
     * Deletes the HOST from the GameService Host list with
     * its identifier
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        executorService.submit(() -> {
            gameService.setGameAsEnded(
                    getIdFromHttpURI(httpExchange)
            );

            try {
                sendEmptyResponse(HttpURLConnection.HTTP_OK, httpExchange);
            } catch (IOException e) {
                System.out.println("Could not send response.");
                e.printStackTrace();
            }
        });
    }
}
