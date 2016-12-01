package arena.tournament.match.result;

import arena.users.IPlayer;

import java.util.List;

/**
 * Created by Simon on 25/11/2016.
 */
public interface IResult {

    ResultType getResultType();

    List<IPlayer> getResultPlayers();
}
