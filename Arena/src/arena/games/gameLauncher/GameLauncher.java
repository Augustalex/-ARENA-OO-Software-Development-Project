package arena.games.gameLauncher;

import arena.games.preInstalledGames.ticTacToe.TicTacToe;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import arena.games.gameInformation.GameInformation;
import tests.RunMatch;

/**
 * Launches a certain game given information about the
 * game via the {@link GameInformation} object.
 */
public class GameLauncher {

    /**
     * Method for launching a game from the ARENA client.
     * A switch case containing all arena.games launches the specific
     * arena.games from their runMatch() method.
     * A gameLibrary.gameInformation object is sent in to let the launcher know
     * which game should be launched.
     * The game is displayed in a new window.
     * @param gameInformation
     */
   public static void launchGame(GameInformation gameInformation){
       StackPane newGamePane = new StackPane();
       Stage stage = new Stage();
       stage.setFullScreen(true);
       stage.setScene(new Scene(newGamePane));
       stage.setTitle(gameInformation.getGameName());
       stage.show();

       switch(gameInformation.getGameName().toLowerCase()){
           case "othello":
               RunMatch.runMatch(newGamePane);
               break;
           case "tic tac toe":case "tictactoe":
               new TicTacToe().start(newGamePane);
           default:

               break;
       }

   }

}
