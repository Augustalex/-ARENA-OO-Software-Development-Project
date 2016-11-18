package tournament.tournamentStyle;

import tournament.ITournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates a TournamentStyle or returns all available Tournament styles in the application.
 */
public class TournamentStyleFactory implements Serializable {
    private static List<ITournamentStyle> styles = new ArrayList<>();

    public static void addTournamentStyle(ITournamentStyle tournamentStyle){
        styles.add(tournamentStyle);
    }

    public static List<ITournamentStyle> getTournamentStyles(){
        return styles.stream().collect(Collectors.toList());
    }

    public static ITournamentStyle newTournamentStyle(){
        return new TournamentStyle();
    }
}
