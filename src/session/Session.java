package session;

import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;
import users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 15/11/2016.
 */
public class Session {

    IPlayer player;
    AppliedTournaments appliedTournaments;

    public Session(){

    }

    public void bindTournamentToPlayer(ITournament tournament, IPlayer player){
        tournament.AddPlayer(player);
        appliedTournaments.applyToTournament(tournament);
    }

    //Inner class AppliedTournaments
    private class AppliedTournaments{

        ArrayList<ITournament> tournaments = new ArrayList<>();

        public AppliedTournaments(){

        }

        public void applyToTournament(ITournament tour){
            tournaments.add(tour);
        }

        public void tournamentNotifier(){

            // to be implemented...
        }
    }
}
