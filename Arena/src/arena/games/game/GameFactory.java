package arena.games.game;

import arena.games.gameInformation.GameInformation;
import arena.games.gameInformation.OthelloGameInformation;
import arena.games.gameServer.GameServer;

/**
 * Creates a new Game object from an installed arena.games Package path.
 *
 */
public class GameFactory {

    static public IGame newMockGame(){
        return new Game().setGameInformation(new OthelloGameInformation());
    }

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
