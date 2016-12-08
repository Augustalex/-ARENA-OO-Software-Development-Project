/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */

package othello.view.dialogs;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * Class creating and showing dialog that lets user setup game information
 * @author Simon
 */
public class SetupGameDialog {
    /**
     * Attributes representing attributes to be collected from user and to be
     * used to create players fir the game
     */
    String player1Name;
    String player2Name;
    String player1Colour;
    String player2Colour;
    int firstPlayerForm;
    int secondPlayerForm; 
    
    /**
     * constructor
     */
    public SetupGameDialog(){}
    
    /**
     * @return name of player 1
     */
    public String getPlayer1Name(){
        return player1Name;
    }
    
    /**
     * @return name of player 2
     */
    public String getPlayer2Name(){
        return player2Name;
    }
    
    /**
     * @return color of player 1
     */
    public String getPlayer1Colour(){
        return player1Colour;
    }
    
    /**
     * @return color of player 2
     */
    public String getPlayer2Colour(){
        return player2Colour;
    }
    
    /**
     * @return player type of player 1
     */
    public int get1stPlayerForm(){
        return firstPlayerForm;
    }
    
    /**
     * @return player type of player 2
     */
    public int get2ndPlayerForm(){
        return secondPlayerForm;
    }
    
    /**
     * Method creates and shows dialog that lets user enter names of players, 
     * choose colours and what types of players to be created.
     * Initialises attributes with collected information.
     */
    public void newGameDialog(){
        Dialog dialog = new Dialog();
        dialog.setTitle("New Game");
        dialog.setHeaderText("Enter player names and choose colour");
        
        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType); 
        
        TextField nameField = new TextField();
        nameField.setPromptText("Player 1 name");
        
        TextField nameField2 = new TextField();
        nameField2.setPromptText("Player 2 name");
        
        RadioButton whiteButton = new RadioButton("White");
        RadioButton blackButton = new RadioButton("Black");
        RadioButton AIPlayerButton = new RadioButton("AI Player");
        RadioButton HumanPlayerButton = new RadioButton("Human Player");
        RadioButton RemotePlayerButton = new RadioButton("Remote Player");
        RadioButton AIPlayerButton1 = new RadioButton("AI Player");
        RadioButton HumanPlayerButton1 = new RadioButton("Human Player");
        RadioButton RemotePlayerButton1 = new RadioButton("Remote Player");
        
        final ToggleGroup groupColours = new ToggleGroup();
        whiteButton.setToggleGroup(groupColours);
        whiteButton.setSelected(true);
        blackButton.setToggleGroup(groupColours);
        
        final ToggleGroup groupPlayer1 = new ToggleGroup();
        AIPlayerButton1.setToggleGroup(groupPlayer1);
        HumanPlayerButton1.setToggleGroup(groupPlayer1);
        RemotePlayerButton1.setToggleGroup(groupPlayer1);
        HumanPlayerButton1.setSelected(true);
        
        final ToggleGroup groupPlayer2 = new ToggleGroup();
        AIPlayerButton.setToggleGroup(groupPlayer2);
        HumanPlayerButton.setToggleGroup(groupPlayer2);
        RemotePlayerButton.setToggleGroup(groupPlayer2);
        HumanPlayerButton.setSelected(true);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        grid.add(new Label("Player 1 name:"),0,0);
        grid.add(nameField, 1, 0);
        grid.add(whiteButton, 1, 1);
        grid.add(blackButton, 1, 2);
        grid.add(AIPlayerButton1, 1, 4);
        grid.add(RemotePlayerButton1, 1, 5);
        grid.add(HumanPlayerButton1, 1, 6);
        grid.add(new Label("Player 2 name:"),2,0);
        grid.add(nameField2, 3, 0);
        grid.add(AIPlayerButton, 3, 1);
        grid.add(RemotePlayerButton, 3, 2);
        grid.add(HumanPlayerButton, 3, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        Optional showAndWait = dialog.showAndWait();
        
        if(whiteButton.isSelected()){
               player1Colour = "white";
               player2Colour = "black";
        }
        
        
        if(blackButton.isSelected()) {
            player1Colour = "black";
            player2Colour = "white";               
        }
        
        if(AIPlayerButton.isSelected()){
            secondPlayerForm = 1; 
        }
        if(RemotePlayerButton.isSelected()){
            secondPlayerForm = 2; 
        }
        if(HumanPlayerButton.isSelected()){
            secondPlayerForm = 0; 
        }
        
        if(AIPlayerButton1.isSelected()){
            firstPlayerForm = 1; 
        }
        if(RemotePlayerButton1.isSelected()){
            firstPlayerForm = 2; 
        }
        if(HumanPlayerButton1.isSelected()){
            firstPlayerForm = 0; 
        }
        
        player1Name = nameField.getText();
        player2Name = nameField2.getText();
    }
}
