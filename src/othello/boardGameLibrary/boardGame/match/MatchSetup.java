package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.GameBoard;
import boardGameLibrary.boardGame.match.exceptions.NoSnapshotSetException;
import boardGameLibrary.players.Player;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by August on 2016-10-26.
 */
public class MatchSetup implements Serializable {

    private Player[] players;
    private String gameType;
    private boolean isOnline = false;

    private BoardSnapshot boardSnapshot = null;

    public MatchSetup(String gameType, Player[] players){
        this.gameType = gameType;
        this.players = players;
    }

    public void storeBoardState(GameBoard board){
        Dimension boundaries = board.getBoundaries();

        this.boardSnapshot = new BoardSnapshot(boundaries);

        for(int y = 0; y < boundaries.height; y++){
            for(int x = 0; x < boundaries.width; x++){
                Point position = new Point(x, y);
                this.boardSnapshot.setOwnerAt(board.getPawn(position).getOwner(), position);
            }
        }
    }

    public BoardSnapshot getSnapshot(){
        if(this.boardSnapshot == null)
            throw new NoSnapshotSetException();
        else
            return this.boardSnapshot;
    }

    public void setBoardSnapshot(BoardSnapshot snapshot){
        this.boardSnapshot = snapshot;
    }

    public boolean snapshotSet(){
        return this.boardSnapshot != null;
    }

    public String getGameType(){
        return this.gameType;
    }

    public boolean isOnline(){
        return isOnline;
    }

    public void setIsOnline(boolean isOnline){
        this.isOnline = isOnline;
    }

    public Player[] getPlayers(){
        return players;
    }
}
