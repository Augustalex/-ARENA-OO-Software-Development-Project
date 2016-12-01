package arena.session;

import arena.tournament.ITournament;

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
        System.out.println("Applied to arena.tournament: " + tour.getTournamentMetaInformation().getName());
        tournaments.add(tour);
    }

    public void tournamentNotifier(){

        // to be implemented...
    }
}
