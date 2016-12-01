package arena.games.game;

import arena.games.gameInformation.GameInformation;
import arena.games.gameServer.GameServer;

/**
 * A container class for information and server classes to a
 * installed game in the Arena.
 */
public interface IGame {

    /**
     * Method for getting the gameinformation of a game
     * @return GameInformation
     */
    GameInformation getGameInformation();

    /**
     * Method for setting the gameinformation to a game-object
     * @param gameInformation
     * @return IGame
     */
    IGame setGameInformation(GameInformation gameInformation);

    /**
     * Method for getting the game server information.
     * @return GameServer
     */
    GameServer getGameServer();

    /**
     * Method for setting the game server to the game-object.
     * @param gameServer
     * @return IGame
     */
    IGame setGameServer(GameServer gameServer);
}
