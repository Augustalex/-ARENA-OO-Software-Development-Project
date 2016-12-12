package arena.tournament.match;

import arena.tournament.match.result.Result;
import arena.tournament.match.result.ResultType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import arena.tournament.match.result.IResult;
import arena.users.IPlayer;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Simon on 25/11/2016.
 */
public class Match implements IMatch {

    static Integer matchID = 0;
    private Lock lock = new ReentrantLock();
    ArrayList<IPlayer> playersInMatch = new MatchMembers().getMatchMembersList();
    private ResultType type;
    private IResult result = new Result(type, playersInMatch);


    public Match(){
        System.out.println("New match with ID " + getMatchID() + " started.");
    }

    @Override
    public Integer getMatchID() {
        return matchID;
    }

    @Override
    public void setUpMatch(IPlayer player1, IPlayer player2) {
        incrementMatchID();
        addPlayerToMatch(player1);
        addPlayerToMatch(player2);

    }

    @Override
    public void addPlayerToMatch(IPlayer player) {
        playersInMatch.add(player);
        System.out.println(player.getName() + " added to match");
    }

    @Override
    public void setResult(ResultType type) {
        result.setResultType(type);
    }

    @Override
    public IResult getMatchResult() {
        return this.result;
    }

    @Override
    public void incrementMatchID() {
        lock.lock();
        try{
            matchID++;
        }
        finally{
            lock.unlock();
        }
    }
}
