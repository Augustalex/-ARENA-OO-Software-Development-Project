package tournament.tournamentConfiguration;

import metaInformation.TournamentMetaInformation;
import tournament.tournamentStyle.TournamentStyle;

/**
 * The configuration of a Tournament not yet created.
 */
public interface ITournamentConfiguration {

    /**
     * Set a Tournament style for the configuration.
     * @param tournamentStyle
     * @return
     */
    ITournamentConfiguration setTournamentStyle(TournamentStyle tournamentStyle);

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
    TournamentStyle getTournamentStyle();

    /**
     * Retrieve the {@link metaInformation.MetaInformation} set on the Tournament Configuration.
     *
     * Might return null if nothing is set already.
     * @return
     */
    TournamentMetaInformation getMetaInformation();

}
