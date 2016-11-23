package gameLibrary;

import gameInformation.GameInformation;
import gameLibrary.game.Game;
import gameLibrary.game.IGame;

/**
 * Creates a new Game object from an installed games Package path.
 *
 */
public class GameFactory {

    public IGame newGame(String gamePackagePath){
        return new Game()
                .setGameInformation(this.getGameInformation(gamePackagePath))
                .setGameServer(this.getGameServer(gamePackagePath));
    }

    private GameServer getGameServer(String gamePackagePath){
        //TODO implement getGameServer

        return null;
    }

    private GameInformation getGameInformation(String gamePackagePath){
        //TODO implement getGameInformation

        return null;
    }
}
