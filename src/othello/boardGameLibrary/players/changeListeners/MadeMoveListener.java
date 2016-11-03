package boardGameLibrary.players.changeListeners;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Waits for a single move to be made and then applies that {@link Move} to the
 * {@link BoardMoveMaker}.
 */
public class MadeMoveListener implements ChangeListener<PlayerMadeMoveEvent> {

    private BoardMoveMaker boardMoveMaker;

    public MadeMoveListener(BoardMoveMaker boardMoveMaker){
        this.boardMoveMaker = boardMoveMaker;
    }

    @Override
    public void changed(ObservableValue<? extends PlayerMadeMoveEvent> observable, PlayerMadeMoveEvent oldValue, PlayerMadeMoveEvent newValue) {

        //Applying move in PlayerMadeMoveEvent to BoardMoveMaker.
        this.boardMoveMaker.makeMove(observable.getValue().getPlayer(), observable.getValue().getMove());

        //Removing self (listener) from list of active listeners.
        observable.removeListener(this);
    }
}
