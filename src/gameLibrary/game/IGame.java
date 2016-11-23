package gameLibrary.game;

import gameInformation.GameInformation;
import gameLibrary.GameServer;

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
