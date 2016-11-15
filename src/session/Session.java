package session;

import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;
import users.Player;
import users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 15/11/2016.
 */
public class Session {

    IPlayer player = new Player(null).createMockPlayerAugust();
    AppliedTournaments appliedTournaments;

    public Session(){
        this.appliedTournaments = new AppliedTournaments();
    }

    public void setPlayer(IPlayer player){
        this.player = player;
    }

    public IPlayer getPlayer(){
        return player;
    }

    public void bindTournamentToPlayer(ITournament tournament, IPlayer player){
        tournament.AddPlayer(player);
        appliedTournaments.applyToTournament(tournament);
    }

    public AppliedTournaments getAppliedTournaments() {
        return appliedTournaments;
    }
}
