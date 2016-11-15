package tournament.tournamentConfiguration;

import metaInformation.TournamentMetaInformation;
import tournament.tournamentStyle.ITournamentStyle;

/**
 * The configuration of a Tournament not yet created.
 */
public interface ITournamentConfiguration {

    /**
     * Set a Tournament style for the configuration.
     * @param ITournamentStyle
     * @return
     */
    ITournamentConfiguration setTournamentStyle(ITournamentStyle ITournamentStyle);

    /**
     * Set {@link metaInformation.MetaInformation} for the Tournament Configuration.
     * @param tournamentMetaInformation
     * @return
     */
    ITournamentConfiguration setMetaInformation(TournamentMetaInformation tournamentMetaInformation);

    /**
     * Retrieve the set style of the Tournament Configuration.
     *
     * Might return null if no style is set.
     * @return
     */
    ITournamentStyle getITournamentStyle();

    /**
     * Retrieve the {@link metaInformation.MetaInformation} set on the Tournament Configuration.
     *
     * Might return null if nothing is set already.
     * @return
     */
    TournamentMetaInformation getMetaInformation();

    /**
     * Return true if all values are set and valid (in this object).
     * @return
     */
    boolean isValid();

    /**
     * Submits the tournament configuration.
     *
     * The implementation might be to send it for review before creating the tournament.
     */
    void submit();

}
