package tournament.tournamentStyle;

import tournament.ITournament;
import tournament.Tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates a TournamentStyle or returns all available Tournament styles in the application.
 */
public class TournamentStyleFactory implements Serializable {

    /**
    * A statical list that contains tournament Styles
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
     * getTournamentStyle returns all tournament Styles
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
    public static ITournamentStyle getPreDefinedStyle(){
        TournamentStyle tournament = new TournamentStyle();
        tournament.setTournamentStyleName("TestTournament");
        tournament.setTournamentSize(64);
        tournament.setGroupSettings();
        GroupSettings groupSettings = tournament.getGroupSettings();
        groupSettings.setGroupAmount(4);
        groupSettings.setMaxWinners(3);
        groupSettings.setRounds(3);
        tournament.setEliminationSettings();
        EliminationSettings eliminationSettings = tournament.getEliminationSettings();
        eliminationSettings.setBestOf(3);
        return tournament;
    }
}
