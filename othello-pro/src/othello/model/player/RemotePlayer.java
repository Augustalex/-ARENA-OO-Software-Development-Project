/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.model.player;

import java.sql.SQLException;
import othello.controller.DatabaseManager;
import othello.controller.ServerManager;
import othello.util.Marker;
import othello.model.gamestate.GameGrid;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import othello.util.Point;

/**
 * RemotePlayer class.
 * The remote player extends the Player class.
 * If a remote player is in the game it's moves gets sent over 
 * a socket connection from a remote server. The connection details for the game
 * server can be found in a SQL database and accessed from a CallableStatement. 
 * @author Johan
 */
public class RemotePlayer extends Player {
    private ServerManager server;
    private DatabaseManager db;
    
    /**
     * Constructor for RemotePlayer.
     * Gets connection details to the game server from a database
     * via the DatabaseManager.
     * @param name player name.
     * @param color players marker-colour. 
     */
    
    public RemotePlayer(String name, String color){
        setName(name);
        setColour(Marker.parseMarker(color));
        db = new DatabaseManager();
        server = new ServerManager(db.getHostName(),db.getPortName());
    }
    
    /**
     * Method returns warning message to HumanPlayer, which is shown through GUI,
     * if player clicks the GUI when it is computer player's turn.
     * @param grid, current gamestate
     * @param i, row of clicked cell
     * @param j, column of clicked cell
     * @return String representing warning message
     */
    @Override
    public String processMove(GameGrid grid, int i, int j){
        return "It's not your turn";
    }
    /**
     * Method requests the gamegrid for a list of all valid moves. It then starts a server 
     * connection and sends moves to the server which returns the chosen move to the game client
     * which then preforms the chosen move. 
     * The method runs in a separate thread to not slow down the UI of the game.
     * @param grid, current gamestate
     */
    @Override
    public void nextMove(GameGrid grid){
        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            ArrayList<Point> list = grid.calculatePlayerMoves(super.getMarker());
            server.setList(list);
            server.startClient();
            Point selection = server.getPoint();
            grid.flipMarkers(super.getMarker(), selection.getPointI(), selection.getPointJ());
        }).start();
    }
}
