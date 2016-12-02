package boardGameLibrary.views.javaFxViews.gameView;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.players.Player;
import boardGameLibrary.viewModel.ViewDimensionBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles a ScoreStatsPane that deals with displaying number of owned pawns
 * per respective player in the game.
 */
public class ScoreStatsController {

    private ScoreStatsPane pane;

    private List<PlayerScoreRowController> rowControllers = new ArrayList<>();

    public ScoreStatsController(){
        this.pane = new ScoreStatsPane();
    }

    /**
     * A new PlayerScoreRow will be created and added with
     * an owner set as the given player.
     * @param player
     */
    public void addPlayerScoreRow(Player player){
        PlayerScoreRowController rowController = new PlayerScoreRowController(player);

        this.getPane().getChildren().add(rowController.getRow());
        this.rowControllers.add(rowController);

        ViewDimensionBinder.bindOneToOneDimension(
                rowController.getRow().minWidthProperty(),
                rowController.getRow().maxWidthProperty(),
                this.getPane().widthProperty()
        );
    }

    /**
     * Binds the score of the player owned row to the actual score
     * determined after every new move made in a BoardMoveMaker.
     * @param moveMaker
     */
    public void updateRowsOnMove(BoardMoveMaker moveMaker){
        for(PlayerScoreRowController rowController : this.rowControllers)
            rowController.updateScoreOnMove(moveMaker);
    }

    /**
     * Returns the ScoreStatsPane.
     * @return
     */
    public ScoreStatsPane getPane(){
        return this.pane;
    }
}