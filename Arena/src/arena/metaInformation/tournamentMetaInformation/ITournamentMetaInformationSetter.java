package arena.metaInformation.tournamentMetaInformation;

import arena.metaInformation.IMetaInformationSetter;
import arena.timeDate.TimeDate;

/**
 * Interface for setting arena.tournament meta information.
 */
public interface ITournamentMetaInformationSetter extends ITournamentMetaInformation, IMetaInformationSetter{

    /**
     * Sets start date of Tournament.
     * @param startDate
     * @return
     */
    ITournamentMetaInformationSetter setStartDate(TimeDate startDate);

}
