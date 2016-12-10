package boardGameLibrary.eventWrappers;

import java.io.Serializable;

/**
 * Created by August on 2016-10-15.
 */
public class BoardMoveEvent implements Serializable{

    private boolean madeLegalMove;
    private boolean noMoreMoves;

    private BoardMoveEvent(boolean madeLegalMove, boolean noMoreMoves){
        this.madeLegalMove = madeLegalMove;
        this.noMoreMoves = noMoreMoves;
    }

    public static BoardMoveEvent illegalMove(){
        return new BoardMoveEvent(false, false);
    }

    public static BoardMoveEvent legalMove(){
        return new BoardMoveEvent(true, false);
    }

    public static BoardMoveEvent noMoreMoves(){
        return new BoardMoveEvent(false, true);
    }

    public boolean getMadeLegalMove(){
        return this.madeLegalMove;
    }

    public boolean getNoMoreMoves(){
        return this.noMoreMoves;
    }

}
