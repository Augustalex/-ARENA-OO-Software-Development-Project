package boardGameLibrary.boardGame.move;

/**
 * Created by August on 2016-10-21.
 */
public class CalculatedMove extends Move {

    /**
     * Number of {@link boardGameLibrary.boardGame.pawn.Pawn} that will be affected in response
     * to this move.
     *
     * Alternatively a higher score may arbitrarily represent a better Move.
     */
    private int score;

    public CalculatedMove(PlayerAction[] actions, int score) {
        super(actions);

        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
