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
 * Class creates and shows a dialog that informs of a draw result
 * @author Simon
 */
public class DrawDialog {
    /**
     * Constructor
     */
    public DrawDialog(){}
    
    /**
     * Dialog informs players that the game ended with a draw. Asks user to 
     * start a new game or quit the game.
     */
    public void newDrawDialog(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game over");
        alert.setHeaderText("Game over!");
        alert.setContentText("Game is a draw!\n"
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
