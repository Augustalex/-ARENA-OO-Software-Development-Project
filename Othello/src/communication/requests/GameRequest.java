package communication.requests;

/**
 * Created by August on 2016-10-31.
 */
public class GameRequest extends Request {

    public GameRequest() {
        super("JOIN GAME", RequestType.QUESTION);
    }
}
