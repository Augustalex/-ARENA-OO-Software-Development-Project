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
}
