package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-09-30.
 */
public class RemotePlayer extends Player {

    private String ip;

    public RemotePlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties) {

    }

    public RemotePlayer setIP(String ipv4){
        this.ip = ipv4;
        return this;
    }
}
