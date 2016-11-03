package boardGameLibrary.boardGame.pawn;

import boardGameLibrary.players.VoidPlayer;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class VoidPawn extends Pawn {

    public VoidPawn() {
        super(new VoidPlayer(), new PawnDisplayModel(Color.TRANSPARENT));
    }
}
