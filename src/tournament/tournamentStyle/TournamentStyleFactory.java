package tournament.tournamentStyle;

import tournament.ITournament;

import java.util.ArrayList;

/**
 * Created by Johan on 2016-11-15.
 */
public class TournamentStyleFactory {
    private static ArrayList<ITournamentStyle> styles = new ArrayList<>();

    public static ITournamentStyle setTournamentStyle(ITournamentStyle tournamentStyle){
        styles.add(tournamentStyle);
    }

    public static ITournamentStyle getTournamentStyle(int index){
        return styles.get(index);
    }

    public static ITournamentStyle newTournamentStyle(){
        return new TournamentStyle();
    }
}
