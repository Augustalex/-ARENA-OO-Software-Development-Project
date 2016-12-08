/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */

package othello.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import othello.util.StatusBarUpdater;

/**
 * Class to create and set layout of a statusbar to be shown in GUI. Statusbar
 * shall show game status and deliver warning messages to player, ie if an 
 * invalid move was made.
 * @author Johan
 */
public class StatusBar{
    /**
     * attribute holds the statusbar created and to be delivered to GUI.
     */
    private HBox statusBar;
    
    /**
     * Constructor initializes the attribute
     */
    public StatusBar(){
        statusBar = new HBox(30);
        statusBar.setPrefHeight(23);
    }
    
    /**
     * @return the statusbar to calling GUI
     */
    public Pane getPane(){
        return statusBar;
    }
    
    /**
     * Sets the status layout in the statusbar by recieving information about 
     * the current game.
     * @param updateObject, contains player values: name, color and score
     * @param markerID, contains the color of the player who is to move
     */
    public void setStatusBarLayout(StatusBarUpdater updateObject, String markerID){
        statusBar.getChildren().clear();
        statusBar.setAlignment(Pos.CENTER_RIGHT);
        statusBar.setPadding(new Insets(3, 40, 3, 30));
        Label warning = new Label("");
        Label player1 = new Label
            (updateObject.getName1() + "'s score (" + updateObject.getColor1() +
                    "): " + updateObject.getScore1());
        Label player2 = new Label
                (updateObject.getName2() + "'s score (" + updateObject.getColor2() +
                    "): " + updateObject.getScore2());
        Label turn = new Label("turn: " + markerID);
        turn.setContentDisplay(ContentDisplay.RIGHT);
        statusBar.getChildren().addAll(warning, player1, player2, turn);
    }
    
    /**
     * Sets a warning message in the statusbar
     * @param message, contains the warning string to be shown.
     */
    public void showWarning(String message){
        Label warning = new Label(message + "       ");
        warning.setStyle("-fx-font-weight: bold;");
        statusBar.getChildren().remove(0);
        statusBar.getChildren().add(0,warning);
    }
    
    /**
     * Removes a warning text set in the statusbar
     */
    public void removeWarning(){
        Label warning = new Label("");
        statusBar.getChildren().remove(0);
        statusBar.getChildren().add(0,warning);
    }
}
