package boardGameLibrary.boardGame.pawn;

import boardGameLibrary.players.Player;

import java.io.Serializable;

/**
 * Created by August on 2016-09-30.
 */
public abstract class Pawn implements Serializable {
    protected Player owner;
    protected PawnDisplayModel displayModel;

    public Pawn(Player owner, PawnDisplayModel displayModel){
        this.owner = owner;
        this.displayModel = displayModel;
    }

    public Player getOwner(){
        return this.owner;
    }

    public void setOwner(Player newOwner){
        this.owner = newOwner;
    }

    public PawnDisplayModel getDisplayModel(){
        return this.displayModel;
    }

    public void setDisplayModel(PawnDisplayModel displayModel){
        this.displayModel = displayModel;
    }
}
