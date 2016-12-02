package boardGameLibrary.players.changeListeners;

import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.boardGame.move.PlayerAction;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import boardGameLibrary.players.Player;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by August on 2016-10-21.
 */
public class CellClickListener implements ChangeListener<CellClickEvent> {

    private ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty;
    private Player player;

    public CellClickListener(ObjectProperty<PlayerMadeMoveEvent> madeMoveProperty, Player player){
        this.madeMoveProperty = madeMoveProperty;
        this.player = player;
    }

    @Override
    public void changed(ObservableValue<? extends CellClickEvent> observable, CellClickEvent oldValue, CellClickEvent newValue) {
        //Getting player action from CellClickEvent
        PlayerAction action = new PlayerAction(observable.getValue().getPosition());

        //Storing new PlayerMadeMoveEvent with the new action.
        this.madeMoveProperty.set(new PlayerMadeMoveEvent(this.player, new Move(new PlayerAction[]{action})));

        //Removing self (listener) from list of active listeners.
        observable.removeListener(this);
    }
}
