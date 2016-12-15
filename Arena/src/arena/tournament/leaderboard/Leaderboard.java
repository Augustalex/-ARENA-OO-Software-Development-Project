package arena.tournament.leaderboard;

import arena.tournament.ITournament;
import arena.users.IPlayer;

import java.util.PriorityQueue;

/**
 * Created by Simon on 12/12/2016.
 */
public class Leaderboard implements ILeaderboard{
    private PriorityQueue<IPlayer> leaderboard = new PriorityQueue<IPlayer>();

    //private ILeaderboard leaderboard = (ILeaderboard) new PriorityQueue<IPlayer>();
    private ITournament tournament;

    public Leaderboard(ITournament tournament){
        this.tournament = tournament;
    }
    @Override
    public ILeaderboard initLeaderboard() {
        for(int i = 0; i < tournament.getAppliedPlayerList().length(); i++){
            leaderboard.add(tournament.getAppliedPlayerList().getPlayerFromList(i));
        }
        return this;
    }

    @Override
    public ILeaderboard updateLeaderboard() {
        //TODO leaderboard update
        return this;
    }

    @Override
    public ILeaderboard getLeaderboard() {
        return this;
    }
}
