package arena.tournament.match;

import javafx.beans.property.ObjectProperty;
import arena.tournament.match.result.IResult;
import arena.users.IPlayer;

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
