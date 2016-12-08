/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.view;

import javafx.scene.layout.BorderPane;


/**
 * A singleton class that contains the main GUI of the Game. The frame should
 * should only be created once and reachable from the controlling GameManager,
 * which is the reason to why it is a singleton object.
 * @author Patric
 */
public class GameFrame {
    /**
     * BorderPane is the main layout of the game frame. It also holds two 
     * attributes which contains the panes that is updated continously during
     * a game.
     */
    private static BorderPane borderPane;
    private GameBoard gameBoard;
    private StatusBar statusBar;
    
    /**
     * Constructor initializing attributes and creates the main GUI frame.
     */
    private GameFrame(){
        borderPane = new BorderPane();
        gameBoard = new GameBoard();
        statusBar = new StatusBar();
        
        borderPane.setLeft(new SideBar().getPane());
        borderPane.setCenter(gameBoard.getPane());
        borderPane.setBottom(statusBar.getPane());
        borderPane.setTop(new TopMenu().getMenu());
    }
    
    /**
     * Lazy initialization for a singleton-object with a holder-class.
     */
    private static class GameFrameHolder{
        private static final GameFrame INSTANCE = new GameFrame();
    }
    
    /**
     * @return the singleton-instance
     */
    public static GameFrame getGrameFrame(){
        return GameFrameHolder.INSTANCE;
    }
    
    /**
     * @return the main gameframe, used to set it as scene in main stage.
     */
    public BorderPane returnGameFrame(){
        return borderPane;
    }
    
    /**
     * @return the gameboard that visualises the game played
     */
    public GameBoard getGameBoard(){
        return gameBoard;
    }
    
    /**
     * @return the statusbar holding information about the current game
     */
    public StatusBar getStatusBar(){
        return statusBar;
    }
    
    /**
     * Method takes an instance of GameBoard and sets it as new board to 
     * visualise a new game.
     * @param board, the board to represent the new gameboard
     */
    public void setGameBoard(GameBoard board){
        gameBoard = board;
        borderPane.setCenter(gameBoard.getPane());
    }
    
    /**
     * Method sets a new statusbar
     * @param statusBar, new instance to represent the new statusbar in gameframe
     */
    public void setStatusBar(StatusBar statusBar){
        this.statusBar = statusBar;
        borderPane.setBottom(this.statusBar.getPane());
    }
    
}
