/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */

package othello.view.dialogs;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import othello.controller.GameManager;

/**
 * Class to create and show a message informing of the result of the game and
 * announcing the winner
 * @author Simon
 */
public class WinnerDialog {
    /**
     * attributes representing information about the winner
     */
    String winner;
    int score;
    
    /**
     * constructor initialising attributes
     * @param name, String representing name of the winner
     * @param score, int representing score of the winner
     */
    public WinnerDialog(String name, int score){
        this.winner = name;
        this.score = score;
    }
    
    /**
     * Method creates and shows dialog that announces the winner of the game.
     * Either quits the game or starts a new game session.
     */
    public void newWinnerDialog(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game over");
        alert.setHeaderText("Game over!");
        alert.setContentText("Player " + winner + " wins with score " + score + "!\n"
        + "New game or quit the game?"
        );
        
        ButtonType quitButton = new ButtonType("Quit");
        ButtonType newGameButton = new ButtonType("New Game");
        
        alert.getButtonTypes().setAll(quitButton, newGameButton);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == quitButton){
            System.out.println("You quit the game!");
            System.exit(0);
        }
        else if(result.get() == newGameButton){
            System.out.println("New game started!");
            new GameManager();
        }

    }
}
