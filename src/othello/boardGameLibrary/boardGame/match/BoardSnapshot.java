package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.pawn.Pawn;
import boardGameLibrary.players.Player;
import boardGameLibrary.players.VoidPlayer;

import java.awt.*;
import java.io.Serializable;

/**
 * Snapshot from a Board state during a game. A board games state may
 * contain a two dimensional container of {@link Pawn}s, in that case
 * the class is not self Serializable. This snapshot however stores only
 * the owner of a cell in a board, and is as such a Serializable class.
 */
public class BoardSnapshot implements Serializable{

    private Player[][] snapshot;

    public BoardSnapshot(int width, int height){
        this.snapshot = new Player[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                this.snapshot[x][y] = new VoidPlayer();
            }
        }
    }

    public BoardSnapshot(Dimension boundaries){
        this(boundaries.width, boundaries.height);
    }

    public void setOwnerAt(Player owner, Point position){
        this.snapshot[position.x][position.y] = owner;
        System.out.println("SET PLAYER " + owner.getName() + " AT " + "[" + position.x + "," + position.y + "]");
    }

    public Player getOwnerAt(Point position){
        return this.snapshot[position.x][position.y];
    }
}
