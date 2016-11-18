package session;

import tournament.ITournament;

import java.util.ArrayList;

/**
 * The tournaments a user have applied to
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
