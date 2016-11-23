package games.game;

import games.gameInformation.GameInformation;
import games.gameServer.GameServer;

/**
 * A container class for information and server classes to a
 * installed game in the Arena.
 */
public interface IGame {

    GameInformation getGameInformation();

    IGame setGameInformation(GameInformation gameInformation);

    GameServer getGameServer();

    IGame setGameServer(GameServer gameServer);
}
