package boardGamePlugins.othello.pawn;

import boardGameLibrary.boardGame.pawn.Pawn;
import boardGameLibrary.boardGame.pawn.PawnDisplayModel;
import boardGameLibrary.players.Player;
import javafx.scene.shape.Circle;

/**
 * Created by August on 2016-09-30.
 */
public class OthelloPawn extends Pawn {

    public OthelloPawn(Player owner){
        super(owner, new PawnDisplayModel(new Circle(), owner.getColor()));
    }

}
