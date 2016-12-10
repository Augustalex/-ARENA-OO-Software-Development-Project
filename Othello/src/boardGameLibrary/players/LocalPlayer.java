package boardGameLibrary.players;
import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import boardGameLibrary.players.changeListeners.CellClickListener;
import boardGameLibrary.players.changeListeners.MadeMoveListener;
import boardGameLibrary.players.changeListeners.OnlineMadeMoveListener;
import hostProviderService.Host;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class LocalPlayer extends Player {

    private boolean isOnline = false;
    private Host[] remotes = new Host[]{};

    public LocalPlayer(String name, Color color) {
        super(name, color);
    }

    /**
     * Establishes a separate {@link Thread} that will wait for a {@link CellClickEvent} and
     * then create a {@link Move}. This will then be used to apply it to the {@link BoardMoveMaker}.
     * @param boardMoveMaker Used to apply the gathered {@link Move}.
     * @param moveProperties
     */
    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties) {
        ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty = new SimpleObjectProperty<>();

        if(isOnline)
            madeMoveProperty.addListener(new OnlineMadeMoveListener(boardMoveMaker, remotes));
        else
            madeMoveProperty.addListener(new MadeMoveListener(boardMoveMaker));

        //Establishing the Thread that will collect a Move from e CellClickEvent.
        new Thread(() -> {

            Platform.runLater(() -> moveProperties.legalMovesProperty().set(boardMoveMaker.getAvailableMoves(this)));
            //Attaching a one time listener.
            moveProperties.cellClickProperty().addListener(new CellClickListener(madeMoveProperty, this));

        }).start();
    }

    public LocalPlayer setToOnlinePlayer(Host[] remotes){
        this.remotes = remotes;
        this.isOnline = true;

        return this;
    }

}
