package arena.tournament.tournamentStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates a TournamentStyle or returns all available Tournament styles in the application.
 */
public class TournamentStyleFactory implements Serializable {

    /**
    * A statical list that contains arena.tournament Styles
    */
    private static List<ITournamentStyle> styles = new ArrayList<>();


    /**
     * addTournamentStyle adds the tournamentStyle to the list Styles.
     * @param tournamentStyle
     */
    public static void addTournamentStyle(ITournamentStyle tournamentStyle){
        styles.add(tournamentStyle);
    }

    /**
     * getTournamentStyle returns all arena.tournament Styles
     */
    public static List<ITournamentStyle> getTournamentStyles(){
        return styles.stream().collect(Collectors.toList());
    }

    /**
     * newTournamentStyle returns a new TournamentStyle object
     */
    public static ITournamentStyle newTournamentStyle(){
        return new TournamentStyle();
    }
    /**
     * newMockTournamentStyle creates a test default style.
     */
    public static ITournamentStyle newMockTournamentStyle(){
        TournamentStyle tournament = new TournamentStyle();
        tournament.setTournamentStyleName("TestTournament");
        tournament.setTournamentSize(64);

        tournament.initateGroupSettings();
        tournament.getGroupSettings().setGroupAmount(4);
        tournament.getGroupSettings().setMaxWinners(3);
        tournament.getGroupSettings().setRounds(3);

        tournament.initateEliminationSettings();
        tournament.getEliminationSettings().setBestOf(3);

        return tournament;
    }
}
