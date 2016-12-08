/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.model.gamestate;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import othello.util.Marker;
import othello.util.EmptyMarker;
import othello.util.Point;


/**
 * Class representing the state of a game. Contains mehthods needed to keep 
 * the gamestate in a legal state and to progress a game, eg by calculating
 * score, checking if moves are legal and executing a move.
 * @author Patric
 */

public class GameGrid extends Observable{
    /**
     * Variables representing the gamestate and its size.
     */
    private int gridSize = 4; //Set default grid size
    private Marker[][] gameState; 
    
    /**
     * Constructor to create a gamegrid of default size
     * @param observer, observer notifying controller when change is made
     * so that it could perform GUI update
     */
    public GameGrid(Observer observer){
        gameState = new Marker[gridSize][gridSize];
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                gameState[i][j] = new EmptyMarker();
            }
        }
        initGameState();
        addObserver(observer);
    }
    
    /**
     * Additional constructor to construct a gamegrid of, by user, chosen size.
     * @param size, chosen size of gameboard
     * @param observer, observer notifying controller when change is made
     * so that it could perform GUI update
     */
    public GameGrid(int size, Observer observer){
        gridSize = size;
        gameState = new Marker[gridSize][gridSize];
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                gameState[i][j] = new EmptyMarker();
            }
        }
        initGameState();
        addObserver(observer);
    }
    
    /**
     * Method returns marker in specific cell in gamegrid.
     * @param i, represent row in gamegrid
     * @param j, represent column in gamegrid
     * @return Marker placed in specefic cell
     */
    public Marker getMarker(int i, int j){
        return gameState[i][j];
    }
    
    /**
     * @return int, representing grid size
     */
    public int getGridSize(){
        return gridSize;
    }
    
    /**
     * Method counts the number of a specific marker on the board
     * @param markerID, represents the kind of marker to count
     * @return int, the quantity of specified marker
     */
    public int countMarker(Marker markerID){
        int score = 0;
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if((gameState[i][j].getColour()).equals(markerID.getColour()))
                    score++;
            }
        }
        return score;
    }
    
    /**
     * Method checks if suggested suggested move is legal, by using a helper
     * method for checking the different directions
     * @param markerToAdd, the marker to be placed in cell
     * @param i, the row in which the marker is to be placed
     * @param j, the column in which the marker is to be placed
     * @return true or false
     */
    public boolean isLegalMove(Marker markerToAdd, int i, int j) {
        //Check that marked square is empty
        if (!gameState[i][j].markerEquals("")) {return false;}
        
        if ((flipIsPossible(i, j, 0, -1, markerToAdd))
                || flipIsPossible(i, j, -1, -1, markerToAdd)
                || flipIsPossible(i, j, 0, 1, markerToAdd)
                || flipIsPossible(i, j, 1, 1, markerToAdd)
                || flipIsPossible(i, j, 1, 0, markerToAdd)
                || flipIsPossible(i, j, -1, 0, markerToAdd)
                || flipIsPossible(i, j, -1, 1, markerToAdd)
                || flipIsPossible(i, j, 1, -1, markerToAdd)) {return true;}
        
        return false;
    }
    
    /**
     * Helpermethod that helps checking if a move is valid in a single direction
     * @param i, the row in which the marker is to be placed
     * @param j, the column in which the marker is to be placed
     * @param possX, value -1, 0 or 1, indicating vertical direction
     * @param possY, value -1, 0 or 1, indicating horisontal direction
     * @param markerToAdd, the type of marker to be placed
     * @return true or false
     */
    private boolean flipIsPossible(int i, int j, int possX, int possY, Marker markerToAdd){
        int x = i + possX;
        int y = j + possY;
        boolean flag = true;
        while (x >= 0 && x < gridSize && y >= 0 && y < gridSize && !gameState[x][y].markerEquals("")){
            if(gameState[x][y].markerEquals(markerToAdd.getColour())){                
                return !flag;
            }            
            x += possX;
            y += possY;
            flag = false;            
        }
        return false;
    }
    
    /**
     * Method performing the marker flip for a valid move that is performed. 
     * Uses a helpermethod to flip markers in all valid directions.
     * @param addedMarker, the marker to be placed and changed into.
     * @param i, the row where the the new marker is placed
     * @param j, the column where the new marker is placed
     */
    public void flipMarkers(Marker addedMarker, int i, int j) {
        //Kolla riktning och byt alla while isOpposite.
        
        gameState[i][j] = addedMarker;
        doTheFlip(i, j, 0, -1, addedMarker);
        doTheFlip(i, j, -1, -1, addedMarker);
        doTheFlip(i, j, -1, 0, addedMarker);
        doTheFlip(i, j, -1, 1, addedMarker);
        doTheFlip(i, j, 0, 1, addedMarker);
        doTheFlip(i, j, 1, 1, addedMarker);
        doTheFlip(i, j, 1, 0, addedMarker);
        doTheFlip(i, j, 1, -1, addedMarker);
        setChanged();
        Platform.runLater(()->{notifyObservers();});
    }
    
    /**
     * Helpermethod for performing the flip of marker in a single direction.
     * @param i, the row where new marker is placed
     * @param j, the column where new marker is placed
     * @param possX value -1, 0 or 1, indicating vertical direction
     * @param possY, value -1, 0 or 1, indicating horisontal direction
     * @param addedMarker 
     */
    private void doTheFlip(int i, int j, int possX, int possY, Marker addedMarker) {
        if (flipIsPossible( i, j, possX, possY, addedMarker)) {
            int x = i + possX;
            int y = j + possY;
            while (x >= 0 && x < gridSize && y >= 0 && y < gridSize && gameState[x][y].isOpposite(addedMarker)){
                gameState[x][y] = addedMarker;                      
                x += possX;
                y += possY;
            }
        }
    }
    
    /**
     * Method checks if gamegrid is full
     * @return  true or false
     */
    public boolean isFull(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(gameState[i][j].markerEquals("")) return false;
            }
        }
        return true;
    }
    
    /**
     * Method calculating all valid moves for a specified marker at a certain
     * movement in the game. Collects all moves in a list which is returned.
     * @param playerMarker, typer of marker to calculate moves for
     * @return ArrayList, a list with valid moves represented as points
     */
    public ArrayList<Point> calculatePlayerMoves(Marker playerMarker){
        ArrayList<Point> pointList = new ArrayList<>();
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(isLegalMove(playerMarker, i, j))
                    pointList.add(new Point(i, j));
            }
        }
        return pointList;
    }
    
    /**
     * Method initialize gamegrid with four initial markers
     */
    private void initGameState(){
        int index1 = gridSize/2-1;
        int index2 = gridSize/2;
        
        gameState[index1][index1] = Marker.parseMarker("black");
        gameState[index1][index2] = Marker.parseMarker("white");
        gameState[index2][index1] = Marker.parseMarker("white");
        gameState[index2][index2] = Marker.parseMarker("black");
    }
}