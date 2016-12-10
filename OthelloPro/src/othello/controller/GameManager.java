/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */

package othello.controller;

import othello.util.StatusBarUpdater;
import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import othello.util.Marker;
import othello.view.StatusBar;
import othello.view.GameBoard;
import othello.model.gamestate.*;
import othello.model.player.*;
import othello.view.Cell;
import othello.view.GameFrame;
import othello.view.dialogs.*;


/**
 * The GameManager constitutes the controller part of the application. 
 * It controls and mediates the flow of data to/from the model part of the 
 * application and to/from the view part of the game.
 * @author Johan
 */
public class GameManager implements Observer{
    /**
     * Attributes created by the GameManager:
     * Player attributes to hold information about each player and to keep track
     * of which player's turn it is.
     * GameGrid attribute communicate the current state of a game. 
     * GameBoard and StatusBar attributes to update GUI
     * Two int attributes to keep track of each player's score.
     */
    private Player player1;
    private Player player2;
    private static Player currentPlayer;
    private static GameGrid gameGrid;
    private GameBoard gameBoard;
    private StatusBar statusBar;
    private int player1Score;
    private int player2Score;
    
    /**
     * Constructor. It sets up the start state for a new game - initialising 
     * all attributes, sets startplayer and startscore, and updating GUI.
     * Finally it asks for an initial move from the startplayer.
     */
    public GameManager(){
        setPlayers();
        currentPlayer = getStartPlayer();
        
        //Construct newGame-gameBoard and assign it to GameFrame
        gameBoard = new GameBoard();
        GameFrame.getGameFrame().setGameBoard(gameBoard);
        gameBoard.initNewBoard();
        gameGrid = new GameGrid(gameBoard.getBoardSize(), this);
        updateBoard(); 
        
        //collect information about the game and set statusbar
        statusBar = GameFrame.getGameFrame().getStatusBar();
        countScore();
        statusBar.setStatusBarLayout
            (new StatusBarUpdater(player1, player2, player1Score, player2Score),
                    currentPlayer.getMarker().getColour());
        
        //Initiate the start of the game;
        currentPlayer.nextMove(gameGrid);
    }
    
    /**
     * This update-method is a reaction on a state-change in the gamegrid, 
     * meaning one player has performed a legal move. The method is called 
     * for every move made, making it a kind of game loop leading the game
     * forward.
     * @param o, observable object notifying of state change
     * @param arg, object sent from observable on notifying call
     */
    @Override
    public void update(Observable o, Object arg) {
        //Erase potential statusbar-warning from last round
        statusBar.removeWarning();
        //Update last events
        updateBoard();
        countScore();
        changeTurn();
        statusBar.setStatusBarLayout
                (new StatusBarUpdater(player1, player2, player1Score, player2Score), 
                        currentPlayer.getMarker().getColour());
        //If end of game present result, otherwise ask next player for a move.
        if(isEndOfGame()){
            presentResult();
            return;
        }
        currentPlayer.nextMove(gameGrid);
        
    }
    
    /**
     * Method collects the gamestate from the gamegrid and passes 
     * information to the gameboard so that the GUI can visualize the 
     * gamestate.
     */
    private void updateBoard(){
        Marker markerID;
        for(int i = 0; i < gameBoard.getBoardSize(); i++){
            for(int j = 0; j < gameBoard.getBoardSize(); j++){
                markerID = gameGrid.getMarker(i, j);
                gameBoard.printCell(markerID.getColour(), i, j);
            }
        }
    }
    
    /**
     * Selects the player with black markers to start.
     * @return Player instance
     */
    private Player getStartPlayer(){
        if(player1.getMarker().getColour().equals("black"))
            return player1;
        else return player2;
    }
    
    /**
     * Method has two responsabilities:
     * 1. If a player has no legal moves, pass the turn over to the other player
     * 2. Check if gameboard is full or no player has any legal moves left,
     * indicating end of game.
     * @return true or false 
     */
    private boolean isEndOfGame(){
        if(gameGrid.isFull()) return true;
        if(gameGrid.calculatePlayerMoves(currentPlayer.getMarker()).isEmpty()){
            Player player = currentPlayer;
            changeTurn();
            statusBar.setStatusBarLayout
                (new StatusBarUpdater(player1, player2, player1Score, player2Score), 
                        currentPlayer.getMarker().getColour());
            statusBar.showWarning("No moves available for " + 
                player.getMarker().getColour() + ". Turn passed over.");
        }
        if(gameGrid.calculatePlayerMoves(currentPlayer.getMarker()).isEmpty())
            return true;
        return false;
    }
    
    /**
     * Changes player turn
     */
    private void changeTurn(){
        if(currentPlayer == player1)
            currentPlayer = player2;
        else 
            currentPlayer = player1;
    }
    /**
     * Collects information about the two players about to play a new game of 
     * Othello. Uses the information to initialize the two player class 
     * attributes.
     */
    private void setPlayers(){
        SetupGameDialog players = new SetupGameDialog();
        players.newGameDialog();
        
        player1 = Player.parsePlayer
            (players.get1stPlayerForm(), players.getPlayer1Name(), players.getPlayer1Colour());
        player2 = Player.parsePlayer
            (players.get2ndPlayerForm(), players.getPlayer2Name(), players.getPlayer2Colour());
              
    }
    
    /**
     * Counts each player's score and assigns it to the two score attributes.
     */
    private void countScore(){
        player1Score = gameGrid.countMarker(player1.getMarker());
        player2Score = gameGrid.countMarker(player2.getMarker());
    }
    
    /**
     * Method controls result of game and announces the result through a dialog.
     */
    private void presentResult(){
        if(isEndOfGame()){
            countScore();
            if(player1Score > player2Score){
                WinnerDialog dialog = new WinnerDialog(player1.getName(), player1Score);
                dialog.newWinnerDialog();
            }
            else if(player2Score > player1Score){
                WinnerDialog dialog = new WinnerDialog(player2.getName(), player2Score);
                dialog.newWinnerDialog();
            }
            else{
                DrawDialog dialog = new DrawDialog();
                dialog.newDrawDialog();
            }
            
        }
    }

   /**
    * Handler class recieve input from GUI representing move selection from
    * human player.
    */
    public static class CellHandler implements EventHandler<InputEvent>{
        /**
         * attributes representing which cell was selected
         */
        private int i;
        private int j;
        
        /**
         * construct, initialising attributes.
         */
        public CellHandler(){
            i = 0;
            j = 0;
        }
        
        /**
         * Overriden Handle-method, collecting information about which cell was
         * selected
         * @param event
         */
        @Override
        public void handle(InputEvent event) {           
            String message;
            Object actionCell = event.getSource();
            i = ((Cell)actionCell).getI();
            j = ((Cell)actionCell).getJ();
            if(!(message = currentPlayer.processMove(gameGrid, i, j)).equals(""))
                GameFrame.getGameFrame().getStatusBar().showWarning(message);
        }
    }
}
