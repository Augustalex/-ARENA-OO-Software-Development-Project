/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */

package othello.view.dialogs;

import java.awt.Desktop;
import java.net.URL;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * Class creating and showing dialog that can direct the user to a webpage where
 * more information about the game Othello can be found.
 * @author Simon
 */
public class HelpDialog {
    /**
     * constructor
     */
    public HelpDialog(){}
    
    /**
     * Helpermethod to open a webpage
     * @param urlString, the URL of the webpage
     */
    private void openHelpPage(String urlString){
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method creating and showing dialog to user, gives the user the 
     * opportunity to continue to a webpage to get more information about the
     * game.
     */
    public void newHelpDialog(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Help & Rules");
        alert.setContentText("Press help to open a browser window with the rules of the game");
        ButtonType helpButton = new ButtonType("Help");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        alert.getButtonTypes().setAll(helpButton, cancelButton);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == helpButton){
            System.out.println("Browser window opened!");
            openHelpPage("http://radagast.se/othello/Help/strategy.html");
        }
        else if(result.get() == cancelButton){
            System.out.println("Help window closed!");
        }
    }
}
