package arena.games.gameLauncher;

import arena.IPInformation.IPInformation;
import arena.games.preInstalledGames.ticTacToe.TicTacToe;
import arena.session.Session;
import arena.streamService.StreamServiceProxy;
import boardGameLibrary.players.LocalPlayer;
import hostProviderService.Host;
import javafx.scene.paint.Color;
import liveStream.LiveStream;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import arena.games.gameInformation.GameInformation;
import othello.Othello;
import tests.RunMatch;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
       //stage.setFullScreen(true);
       stage.setScene(new Scene(newGamePane, 600, 600));
       stage.setTitle(gameInformation.getGameName());
       stage.show();

       switch(gameInformation.getGameName().toLowerCase()){
           case "othello":
               //RunMatch.runMatch(newGamePane);

               Map<Host, LocalPlayer> players = new HashMap<>();

               LocalPlayer johan = new LocalPlayer("Simon", Color.BLUE);
               LocalPlayer august = new LocalPlayer("August", Color.TOMATO);
               LocalPlayer carlos = new LocalPlayer("Carlos", Color.PINK);

               players.put(new Host("172.20.10.8", 3000), johan);
               players.put(new Host("172.20.10.3", 3000), august);
               //players.put(new Host("10.10.71.135", 3000), carlos);


               RunMatch.runOnlineMatchTest(newGamePane, players, august);

               int liveStreamPort = 1995;

               new Thread(() -> {
                   LiveStream liveStream = new LiveStream(newGamePane, liveStreamPort);
                   liveStream.smartStream();
               }).start();

               try {
                   Session.getSession().getStreamService().sendLiveStream(new Host(
                           Inet4Address.getLocalHost().getHostAddress(),
                           liveStreamPort
                   ));

               } catch (UnknownHostException e) {
                   System.out.println("Could not retrieve IP address of local host, for starting a live stream.");
                   e.printStackTrace();
               }
               break;
           case "tic tac toe":case "tictactoe":
               new TicTacToe().start(newGamePane);
               break;
           case "othellopro":
               new Othello().start(stage);
               break;
           default:
               System.out.println("No game by that name.");
               break;
       }
   }

   public static void launchMultiplayerGame(GameInformation gameInformation, IPInformation... ipInformation){
       System.out.println("a a a amazing");
   }

}
