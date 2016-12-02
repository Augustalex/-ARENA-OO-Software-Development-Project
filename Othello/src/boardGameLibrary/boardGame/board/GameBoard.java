package boardGameLibrary.boardGame.board;

import boardGameLibrary.boardGame.match.BoardSnapshot;
import boardGameLibrary.boardGame.pawn.Pawn;
import boardGameLibrary.eventWrappers.CellChangeEvent;
import boardGameLibrary.players.VoidPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by August on 2016-10-13.
 */
public abstract class GameBoard<T extends Pawn> implements Serializable{

    protected Vector<Vector<T>> board;

    private ObservableList<CellChangeEvent> cellChangeObserver = FXCollections.observableArrayList();

    private int width;
    private int height;

    public GameBoard(int width, int height){
        this.board = new Vector<>(width);

        for(int x = 0; x < width; x++) {
            this.board.add(new Vector<>(height));
            for(int y = 0; y < height; y++){
                this.board.get(x).add(null);
            }
        }

        this.width = width;
        this.height = height;
    }

    public boolean withinBounds(Point position){
        return (position.x >= 0 && position.x <= this.width-1)
            && (position.y >= 0 && position.y <= this.height-1);
    }

    public Dimension getBoundaries(){
        return new Dimension(this.width, this.height);
    }

    public Pawn getPawn(Point position){
        return this.board.get(position.x).get(position.y);
    }

    public void setPawn(Point position, T newPawn){
        this.board.get(position.x).set(position.y, newPawn);
        this.cellChangeObserver.add(new CellChangeEvent(position));
        this.cellChangeObserver.clear();
    }

    public boolean isEmpty(Point position){
        return this.board.get(position.x).get(position.y).getOwner() instanceof VoidPlayer;
    }

    public ObservableList<CellChangeEvent> getCellChangeObserver(){
        return this.cellChangeObserver;
    }

    public abstract void restoreGameBoard(BoardSnapshot snapshot);

}
