/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.model.player;

import othello.util.Marker;
import othello.model.gamestate.GameGrid;

/**
 * Abstract class for players.
 * Can either be: Human, local AI or remote player.
 * Contains markerID which dictates player colour
 * and name which is the players name
 * @author Simon
 */
public abstract class Player {
    /**
     * Common attributes for all player types.
     */
    private Marker markerID;
    private String name;
    
    /**
     * Constructor for Player class.
     */
    public Player(){}
    
    /**
     * Method for choosing which player to create
     * @param playerType, representing a player type
     * @param name, the name of the player
     * @param color, the colour of the player
     * @return Player, holding an initialised subclass representing a concrete
     * player type
     */
    public static Player parsePlayer(int playerType, String name, String color){
        switch(playerType){
            case 0:
                return new HumanPlayer(name, color);
            case 1:
                return new LocalComputerPlayer(name, color);
            default:
                return new RemotePlayer(name, color);//.newRemotePlayer("localhost", 8080);    
        }
    }
    
    /**
     * Method for setting player colour.
     * @param markerID 
     */
    final public void setColour(Marker markerID){
         this.markerID = markerID;
    }
    /**
     * Method for setting the playername.
     * @param inputName 
     */
    final public void setName(String inputName){
        this.name = inputName;
    }
    
    /**
     * Method for getting player name.
     * @return String name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Method for getting player colour, or markerID.
     * @return Marker markerID, the players colour. 
     */
    
    public Marker getMarker(){
        return markerID;
    }    
    
    /**
     * Abstract method for requesting the gamegrid for a list of all valid moves.
     * @param grid , gamegrid.
     */
    public abstract void nextMove(GameGrid grid);
    
    /**
     * Method to check if suggested move through GUI input is valid.
     * @param grid, current gamestate
     * @param i, row of suggested move
     * @param j, column of suggested move
     * @return String to represent a warning to be presented in GUI
     */
    public abstract String processMove(GameGrid grid, int i, int j);
}
