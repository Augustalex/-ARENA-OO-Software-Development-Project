package tournament.match.result;

import users.IPlayer;

import java.util.List;

/**
 * Created by Simon on 25/11/2016.
 */
public class Result implements IResult{
    private ResultType type;
    private List<IPlayer> resultPlayer;

    public Result(ResultType type, List<IPlayer> resultPlayers){
        this.type = type;
        this.resultPlayer = resultPlayers;
    }

    @Override
    public ResultType getResultType() {
        return type;
    }

    @Override
    public List<IPlayer> getResultPlayers() {
        return resultPlayer;
    }
}
