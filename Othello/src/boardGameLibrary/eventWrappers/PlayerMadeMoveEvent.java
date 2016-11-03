package boardGameLibrary.eventWrappers;

import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.players.Player;

/**
 * Created by August on 2016-10-15.
 */
public class PlayerMadeMoveEvent {

    private Move move;
    private Player player;

    public PlayerMadeMoveEvent(Player player, Move move){
        this.player = player;
        this.move = move;
    }

    public Move getMove(){
        return this.move;
    }

    public Player getPlayer(){
        return this.player;
    }
}
