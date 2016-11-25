package tournament.match;

import javafx.beans.property.ObjectProperty;
import tournament.match.result.IResult;
import users.IPlayer;

/**
 * Created by Simon on 24/11/2016.
 */
public interface IMatch {
    //list of players
    //Game

    void setUpMatch();

    ObjectProperty<IResult> resultProperty();

    void addPlayerToMatch(IPlayer player);

}
