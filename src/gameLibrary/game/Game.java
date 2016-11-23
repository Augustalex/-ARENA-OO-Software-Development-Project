package gameLibrary.game;

import gameInformation.GameInformation;
import gameLibrary.GameServer;

/**
 * Concrete implementation of {@link IGame}.
 */
public class Game implements IGame{

    private GameServer gameServer = null;
    private GameInformation gameInformation = null;

    public Game(){
    }

    public Game(GameServer gameServer, GameInformation gameInformation){
        this.gameInformation = gameInformation;
        this.gameServer = gameServer;
    }

    @Override
    public GameInformation getGameInformation() {
        return this.gameInformation;
    }

    @Override
    public IGame setGameInformation(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
        return this;
    }

    @Override
    public GameServer getGameServer() {
        return this.gameServer;
    }

    @Override
    public IGame setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
        return this;
    }
}
