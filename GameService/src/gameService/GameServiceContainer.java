package gameService;

import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-12-02.
 */
public class GameServiceContainer extends ReSTContainer {

    public GameServiceContainer(int port) throws IOException {
        super(port);

        this.createContext("/");
    }
}
