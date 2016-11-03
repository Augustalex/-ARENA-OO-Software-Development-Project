package boardGameLibrary.views.javaFxViews.gameView;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.players.Player;
import javafx.scene.control.Label;

/**
 * Display score stats for on a row given a Player as its owner.
 */
public class PlayerScoreRowController {

    private Player owner;
    private PlayerScoreRow row;

    public PlayerScoreRowController(Player owner){
        this.row = new PlayerScoreRow();
        this.owner = owner;

        this.row.setName(owner.getName());
        this.row.setScore(0);

        this.setupRowLabelsDimensionBinding();
    }

    /**
     * Binds the rows score label to the calculated score of a player following
     * a move made on the given BoardMoveMaker.
     * @param moveMaker
     */
    public void updateScoreOnMove(BoardMoveMaker moveMaker){
        moveMaker.BoardMoveEventProperty().addListener(e -> {
            this.row.setScore(moveMaker.numberOfPawnsOwned(this.owner));
        });
    }

    /**
     * Returns the PlayerScoreRow.
     * @return
     */
    public PlayerScoreRow getRow(){
        return this.row;
    }

    private void setupRowLabelsDimensionBinding(){
        this.setupLabelDimensionBinding(this.getRow().getNameLabel());
        this.setupLabelDimensionBinding(this.getRow().getScoreLabel());
    }

    private void setupLabelDimensionBinding(Label label) {
        label.maxWidthProperty().bind(this.getRow().widthProperty().divide(2));
        label.minWidthProperty().bind(this.getRow().widthProperty().divide(2));
    }

}
