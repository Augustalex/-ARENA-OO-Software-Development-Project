package gameLauncher;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GameInformation;

import static tests.RunMatch.runMatch;

/**
 * Created by Simon on 05/11/2016.
 */
public class GameLauncher {

   public static void launchGame(GameInformation gameInformation){
       StackPane newGamePane = new StackPane();
       Stage stage = new Stage();
       stage.setScene(new Scene(newGamePane));
       stage.setTitle(gameInformation.getGameName());
       stage.show();

       switch(gameInformation.getGameName().toLowerCase()){
           case "othello":
               runMatch(newGamePane);
               break;
           default:

               break;
       }

   }

}
