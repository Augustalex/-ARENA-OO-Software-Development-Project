package tournament.tournamentStyle;

import tournament.Tournament;

/**
 * Defines the type and style of tournament to be played.
 *
 * Might describe the way a Tournament Map may be created and
 * other related aspect to setting up a new Tournament.
 */
public interface ITournamentStyle {

    void setGroupRound(boolean value);
    boolean getGroupRound();
    void setEliminationRound(boolean value);
    boolean getEliminationRound();


    ITournamentStyle getTournamentStyle();
    // void setTournamentStyle(ITournamentStyle tournamentStyle);
    void createMapPreference();
}
