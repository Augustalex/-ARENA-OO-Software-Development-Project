package boardGameLibrary.players.changeListeners;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.eventWrappers.PlayerMadeMoveEvent;
import hostProviderService.Host;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by August on 2016-12-10.
 */
public class OnlineMadeMoveListener implements ChangeListener<PlayerMadeMoveEvent>{

    private final Host[] remotes;
    private final BoardMoveMaker moveMaker;

    public OnlineMadeMoveListener(BoardMoveMaker moveMaker, Host[] remotes){
        this.moveMaker = moveMaker;
        this.remotes = remotes;
    }

    @Override
    public void changed(ObservableValue<? extends PlayerMadeMoveEvent> observable, PlayerMadeMoveEvent oldValue, PlayerMadeMoveEvent newValue) {
        for(Host remote : remotes){
            try {
                System.out.println("Trying to connect to: " + remote.getURL());
                new ObjectOutputStream(
                        new Socket(
                                remote.address,
                                remote.port
                        ).getOutputStream()
                ).writeObject(newValue.getMove());
                System.out.println("Move sent.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Applying move in PlayerMadeMoveEvent to BoardMoveMaker.
        moveMaker.makeMove(observable.getValue().getPlayer(), observable.getValue().getMove());

        //Removing self (listener) from list of active listeners.
        observable.removeListener(this);
    }
}
