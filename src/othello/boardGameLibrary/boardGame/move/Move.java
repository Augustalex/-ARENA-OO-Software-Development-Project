package boardGameLibrary.boardGame.move;

/**
 * Move class is a wrapper, or holder, class for a number of {@link PlayerAction}s. A board game
 * might include one single action as a more, or it may hold several. This class is independent on
 * which ever number of actions are required for a legal move.
 */
public class Move {

    private PlayerAction[] actions;

    public Move(PlayerAction[] actions){
        this.actions = actions;
    }

    /**
     * Returns an array of all {@link PlayerAction}s instantiated from
     * the class constructor.
     * @return
     */
    public PlayerAction[] getActions(){
        return this.actions;
    }
}
