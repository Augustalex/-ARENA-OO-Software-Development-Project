package views.tournament.extendedTournamentView;

import arena.tournament.ITournament;
import spectate.SpectateTable;

/**
 * Created by Simon on 02/12/2016.
 */
public class MatchList {
    SpectateTable matchList = new SpectateTable();

    public MatchList(ITournament tournament){
        matchList.getAvailibleMatches(tournament);
    }
}
