package arena.games.game;

import arena.games.gameInformation.GameInformation;
import arena.games.gameInformation.OthelloGameInformation;
import arena.games.gameInformation.OthelloProGameInformation;
import arena.games.gameInformation.TicTacToeGameInformation;
import arena.games.gameServer.GameServer;

/**
 * Creates a new Game object from an installed arena.games Package path.
 *
 */
public class GameFactory {

    public IGame newGame(String gamePackagePath){
        return new Game()
                .setGameInformation(this.getGameInformation(gamePackagePath))
                .setGameServer(this.getGameServer(gamePackagePath));
    }

    public static IGame newMockOthelloGame(){
        return new Game()
                .setGameInformation(new OthelloGameInformation())
                .setGameServer(null);
    }

    public static IGame newMockTicTacToe(){
        return new Game()
                .setGameInformation(new TicTacToeGameInformation())
                .setGameServer(null);
    }

    public static IGame newMockOthelloPro(){
        return new Game()
                .setGameInformation(new OthelloProGameInformation())
                .setGameServer(null);
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
