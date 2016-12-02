package gameService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import hostProviderService.Host;
import rest.ReST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by August on 2016-12-02.
 */
public class GameServiceAPI extends ReST {

    protected final GameService gameService;
    protected ExecutorService executorService = Executors.newFixedThreadPool(100);

    public GameServiceAPI(GameService service){
        this.gameService = service;
    }

    /**
     * Retrieves all active game server hosts.
     *
     * It does not retrieve host ids.. So information provided by
     * this API call cannot be used to later close the listed hosts.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        executorService.submit(() -> {
            try {
                sendStringContentResponse(
                        HttpURLConnection.HTTP_OK,
                        new Gson().toJson(
                                gameService.getAllActiveGames(),
                                Host[].class
                        ),
                        httpExchange
                );
            } catch (IOException e) {
                System.out.println("Could not send response.");
                e.printStackTrace();
            }
        });
    }

    /**
     * Creates a new game server based on provided PlayerHostInformation objects (as JSON).
     *
     * Returns Host Id that can be used to get information about the hosts that hosts the new Game Server!
     *
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {
        executorService.submit(() -> {
            try {
                Gson gson = new Gson();
                String gameServerHostId = gameService.startNewGame(
                        gson.fromJson(
                                getStringBodyFromHttpExchange(httpExchange),
                                PlayerHostInformation[].class
                        )
                );

                sendStringContentResponse(HttpURLConnection.HTTP_OK, gameServerHostId, httpExchange);
            } catch (IOException e) {
                System.out.println("Could not send response.");
                e.printStackTrace();
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
