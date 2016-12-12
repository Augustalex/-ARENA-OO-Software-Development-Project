package arena.tournament.match;

import arena.tournament.match.result.ResultType;
import javafx.beans.property.ObjectProperty;
import arena.tournament.match.result.IResult;
import arena.users.IPlayer;

/**
 * Created by Simon on 24/11/2016.
 */
public interface IMatch {
    //list of players
    //Game

    Integer getMatchID();

    void setUpMatch(IPlayer player1, IPlayer player2);

    void addPlayerToMatch(IPlayer player);

    void setResult(ResultType type);

    IResult getMatchResult();

    void incrementMatchID();

}
