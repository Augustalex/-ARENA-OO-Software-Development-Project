package session;

import tournament.ITournament;

import java.util.ArrayList;

/**
 * Created by Simon on 15/11/2016.
 */
public class AppliedTournaments {
    ArrayList<ITournament> tournaments = new ArrayList<>();

    public AppliedTournaments(){

    }

    //public void getTournaments()

    public void applyToTournament(ITournament tour){
        System.out.println("Applied to tournament.");
        tournaments.add(tour);
    }

    public void tournamentNotifier(){

        // to be implemented...
    }
}
